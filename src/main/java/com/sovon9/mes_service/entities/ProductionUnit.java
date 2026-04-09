package com.sovon9.mes_service.entities;

import com.sovon9.mes_service.utility.GlobalUtil;
import jakarta.persistence.*;

@Entity
@Table(name = "production_unit")
public class ProductionUnit implements Node{

    @Transient
    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long productionUnitId;

    @Column
    private String unit;

    @Column(name = "production_line_id")
    private Long productionLineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_line_id", insertable=false, updatable=false)
    private ProductionLine productionLine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getProductionUnitId() {
        return productionUnitId;
    }

    public void setProductionUnitId(Long productionUnitId) {
        this.productionUnitId = productionUnitId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getProductionLineId() {
        return productionLineId;
    }

    public void setProductionLineId(Long productionLineId) {
        this.productionLineId = productionLineId;
    }

    public ProductionLine getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(ProductionLine productionLine) {
        this.productionLine = productionLine;
    }

    @PostLoad
    public void postLoad()
    {
        id= GlobalUtil.toGlobalId("ProductionUnit", productionUnitId);
    }
}
