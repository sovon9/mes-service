package com.sovon9.mes_service.dto;

import com.sovon9.mes_service.entities.ProductionLine;

import java.util.List;

public class DepartmentDto {
    private Long departmentId;
    private String department;
    private List<ProductionLine> productionLines;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<ProductionLine> getProductionLines() {
        return productionLines;
    }

    public void setProductionLines(List<ProductionLine> productionLines) {
        this.productionLines = productionLines;
    }
}
