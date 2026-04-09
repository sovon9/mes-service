package com.sovon9.mes_service.dto;

import com.sovon9.mes_service.entities.Department;
import com.sovon9.mes_service.entities.ProductionUnit;

import java.util.List;

public class ProductionLineDto {
    private Long productionLineId;
    private Long departmentId;
    private String line;
    private Department department;
    private List<ProductionUnit> productionUnits;

    public Long getProductionLineId() {
        return productionLineId;
    }

    public void setProductionLineId(Long productionLineId) {
        this.productionLineId = productionLineId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<ProductionUnit> getProductionUnits() {
        return productionUnits;
    }

    public void setProductionUnits(List<ProductionUnit> productionUnits) {
        this.productionUnits = productionUnits;
    }
}
