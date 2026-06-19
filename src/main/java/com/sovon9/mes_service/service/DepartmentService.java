package com.sovon9.mes_service.service;

import com.sovon9.mes_service.entities.Department;
import com.sovon9.mes_service.repositories.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepo;

    public DepartmentService(DepartmentRepository departmentRepo)
    {
        this.departmentRepo=departmentRepo;
    }

    public Page<Department> getAllDepartmentData(int pageNo, int pageSize, String sortBy)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        return departmentRepo.findAll(pageable);
    }

}
