package com.sovon9.mes_service.service;

import com.sovon9.mes_service.entities.ProductionUnit;
import com.sovon9.mes_service.repositories.ProductionLineRepository;
import com.sovon9.mes_service.repositories.ProductionUnitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductionUnitService {

    private ProductionUnitRepository productionUnitRepo;

    public ProductionUnitService(ProductionUnitRepository productionUnitRepo)
    {
        this.productionUnitRepo=productionUnitRepo;
    }

    public Page<ProductionUnit> getAllProductionUnitData(int pageNo, int pageSize, String sortBy)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        return productionUnitRepo.findAll(pageable);
    }

}
