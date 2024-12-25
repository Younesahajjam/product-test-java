package com.example.test.service.servicelmp;

 import com.example.test.dto.CostumerDto;
 import com.example.test.entity.Costumer;
 import com.example.test.helper.SpecificationBuilder;
 import com.example.test.mapper.MapperCostumer;
 import com.example.test.repository.CostumerRepo;
 import com.example.test.service.ServiceTest;

 import com.example.test.type.SortDirection;

 import lombok.RequiredArgsConstructor;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Pageable;
 import org.springframework.data.domain.Sort;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.stereotype.Service;

 import java.util.List;
 import java.util.Objects;
 import java.util.Optional;
 import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceTestIMP implements ServiceTest {

    private final CostumerRepo testRepo;

    private final MapperCostumer mapperTest;

    private final SpecificationBuilder<Costumer> specificationBuilder;

    @Override
    public CostumerDto create(CostumerDto testDTO) {
        Costumer test =mapperTest.toEntity(testDTO);
        Costumer saveEntiy=testRepo.save(test);

        return mapperTest.toDto(saveEntiy);
    }

    @Override
    public List<CostumerDto> findAll() {

        List<Costumer> testList=testRepo.findAll();

        return testList.stream().map(mapperTest::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CostumerDto findById(Long id) {

        Optional<Costumer> test=testRepo.findById(id);


        return test.map(mapperTest::toDto).orElse(null) ;
    }

    @Override
    public CostumerDto update(CostumerDto testDTO) {
        Optional<Costumer> test=testRepo.findById(testDTO.getOid());
        if(test.isPresent()){
            Costumer test1=test.get();
            test1.setOid(testDTO.getOid());
            test1.setName(testDTO.getName());

            Costumer update= testRepo.save(test1);
           return mapperTest.toDto(update);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        testRepo.deleteById(id);

    }


    @Override
    public Page<CostumerDto> searchWithCriteria(String email, int page, int size, String sortField, SortDirection sortDirection) {
        Specification<Costumer> spec = buildSpecifications(email);
        Pageable pageable = buildPageable(page, size, sortField, sortDirection);


        Page<Costumer> pageResult = testRepo.findAll(spec, pageable);
        return pageResult.map(mapperTest::toDto);
    }

    private Pageable buildPageable(int page, int size, String sortField, SortDirection sortDirection) {
        Sort sort = Sort.by(Objects.equals(sortDirection.label, "desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortField);
        return PageRequest.of(page, size, sort);
    }

    private Specification<Costumer> buildSpecifications(String name) {
        Specification<Costumer> spec = Specification.where(null);
        if (name != null && !name.isEmpty()) {
            spec = spec.and(specificationBuilder.equalSpec(spec, "email", name));

        }
        System.out.println("Specification: " + spec);
        return spec;
    }



}

