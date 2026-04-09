package com.sovon9.mes_service.entities;

import com.sovon9.mes_service.utility.GlobalUtil;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "production_line")
public class ProductionLine implements Node{

    @Transient
    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long productionLineId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column
    private String line;

    @ManyToOne
    @JoinColumn(name = "department_id", insertable=false, updatable=false)
    private Department department;

//    private List<ProductionExecutionPath> executionPathList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_line_id", referencedColumnName = "id")
    private List<ProductionUnit> productionUnits;

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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Long getProductionLineId() {
        return productionLineId;
    }

    public void setProductionLineId(Long productionLineId) {
        this.productionLineId = productionLineId;
    }

    @PostLoad
    public void postLoad()
    {
        id= GlobalUtil.toGlobalId("ProductionLine", productionLineId);
    }
}
