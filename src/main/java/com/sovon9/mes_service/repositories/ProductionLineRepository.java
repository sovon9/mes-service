package com.sovon9.mes_service.repositories;

import com.sovon9.mes_service.entities.ProductionLine;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionLineRepository extends JpaRepository<ProductionLine, Long>, JpaSpecificationExecutor<ProductionLine> {
    public Window<ProductionLine> getBy(ScrollPosition position, Limit limit, Sort sort);
    
    List<ProductionLine> findByDepartmentIdIn(List<Long> departmentIds);
}
