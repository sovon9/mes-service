package com.sovon9.mes_service.service;

import com.sovon9.mes_service.entities.ProductionLine;
import com.sovon9.mes_service.repositories.ProductionLineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductionLineService {

    private final ProductionLineRepository productionLineRepo;

    public ProductionLineService(ProductionLineRepository productionLineRepo)
    {
        this.productionLineRepo=productionLineRepo;
    }

    public Page<ProductionLine> getAllproductionLineData(int pageNo, int pageSize, String sortBy)
    {
        Pageable pageable = PageRequest.of(pageSize, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        return productionLineRepo.findAll(pageable);
    }

}
