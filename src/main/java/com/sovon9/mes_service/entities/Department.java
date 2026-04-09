package com.sovon9.mes_service.entities;

import com.sovon9.mes_service.utility.GlobalUtil;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "department")
public class Department implements Node{

    @Transient
    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long departmentId;

    @Column
    private String department;

    @OneToMany(mappedBy = "department")
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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

    @PostLoad
    public void postLoad()
    {
        id= GlobalUtil.toGlobalId("Department", departmentId);
    }
}
