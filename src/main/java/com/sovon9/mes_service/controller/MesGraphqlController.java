package com.sovon9.mes_service.controller;

import com.sovon9.mes_service.entities.Department;
import com.sovon9.mes_service.entities.ProductionLine;
import com.sovon9.mes_service.entities.ProductionUnit;
import com.sovon9.mes_service.repositories.DepartmentRepository;
import com.sovon9.mes_service.repositories.ProductionLineRepository;
import com.sovon9.mes_service.repositories.ProductionUnitRepository;
import com.sovon9.mes_service.utility.QueryBuilderUtil;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MesGraphqlController{

    private final DepartmentRepository departmentRepository;
    private final ProductionLineRepository productionLineRepository;
    private final ProductionUnitRepository productionUnitRepository;

    public MesGraphqlController(DepartmentRepository departmentRepository, ProductionLineRepository productionLineRepository, ProductionUnitRepository productionUnitRepository)
    {
        this.departmentRepository=departmentRepository;
        this.productionLineRepository=productionLineRepository;
        this.productionUnitRepository=productionUnitRepository;
    }

    @QueryMapping("departments")
    public Window<Department> departments(ScrollSubrange subrange, @Argument Map<String, Object> where, @Argument Map<String, Object> order)
    {
        ScrollPosition scrollPosition = subrange.position().orElse(ScrollPosition.offset());
        int limit = subrange.count().orElse(10);

        Sort.Direction direction = Sort.Direction.ASC;
        if(!subrange.forward())
        {
            direction=Sort.Direction.DESC;
        }

        Sort sort = QueryBuilderUtil.buildSort(order, "departmentId", direction);
        Specification<Department> departmentSpecification = QueryBuilderUtil.buildSpecification(where);

        if(departmentSpecification==null)
        {
            return departmentRepository.findBy(scrollPosition, Limit.of(limit), sort);
        }
        return departmentRepository.findBy(departmentSpecification, q->q.limit(limit).sortBy(sort).scroll(scrollPosition));
    }

    @BatchMapping(typeName = "Department", field = "productionLines")
    public Map<Department, Window<ProductionLine>> productionLines(List<Department> departments) {
        List<Long> departmentIds = departments.stream()
                .map(Department::getDepartmentId)
                .collect(Collectors.toList());

        List<ProductionLine> allLines = productionLineRepository.findByDepartmentIdIn(departmentIds);

        Map<Long, List<ProductionLine>> lineMap = allLines.stream()
                .collect(Collectors.groupingBy(ProductionLine::getDepartmentId));

        Map<Department, Window<ProductionLine>> resultMap = new HashMap<>();
        for (Department department : departments) {
            List<ProductionLine> lines = lineMap.getOrDefault(department.getDepartmentId(), List.of());
            resultMap.put(department, Window.from(lines, index -> ScrollPosition.offset(index)));
        }

        return resultMap;
    }

    @BatchMapping(typeName = "ProductionLine", field = "productionUnits")
    public Map<ProductionLine, Window<ProductionUnit>> productionUnits(List<ProductionLine> productionLines) {
        List<Long> lineIds = productionLines.stream()
                .map(ProductionLine::getProductionLineId)
                .collect(Collectors.toList());

        List<ProductionUnit> allUnits = productionUnitRepository.findByProductionLineIdIn(lineIds);

        Map<Long, List<ProductionUnit>> unitMap = allUnits.stream()
                .collect(Collectors.groupingBy(ProductionUnit::getProductionLineId));

        Map<ProductionLine, Window<ProductionUnit>> resultMap = new HashMap<>();
        for (ProductionLine line : productionLines) {
            List<ProductionUnit> units = unitMap.getOrDefault(line.getProductionLineId(), List.of());
            resultMap.put(line, Window.from(units, index -> ScrollPosition.offset(index)));
        }

        return resultMap;
    }

}
