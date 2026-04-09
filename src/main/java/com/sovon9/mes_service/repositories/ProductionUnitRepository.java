package com.sovon9.mes_service.repositories;

import com.sovon9.mes_service.entities.ProductionUnit;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionUnitRepository extends JpaRepository<ProductionUnit, Long>, JpaSpecificationExecutor<ProductionUnit> {
    public Window<ProductionUnit> getBy(ScrollPosition position, Limit limit, Sort sort);
    
    List<ProductionUnit> findByProductionLineIdIn(List<Long> productionLineIds);
}
