package com.example.test.service;

import com.example.test.dto.CostumerDto;
import com.example.test.type.SortDirection;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ServiceTest {

     CostumerDto create(CostumerDto testDTO);
     List<CostumerDto> findAll();
     CostumerDto findById(Long id);
     CostumerDto update(CostumerDto testDTO);
     void delete(Long id);
     Page<CostumerDto> searchWithCriteria(String name, int page, int size, String sortField, SortDirection sortDirection);
}
