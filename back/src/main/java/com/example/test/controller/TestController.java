package com.example.test.controller;

import com.example.test.dto.CostumerDto;
import com.example.test.exeption.ApiRequestException;
import com.example.test.service.ServiceTest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {

    private final ServiceTest serviceTest;
@PostMapping("/add")
    public ResponseEntity<CostumerDto> createTest(@RequestBody CostumerDto testDTO) {
    try {
        CostumerDto testDTO1= serviceTest.create(testDTO);
    return new  ResponseEntity<>(testDTO1, HttpStatus.CREATED);
    } catch (ApiRequestException e) {
        throw new ApiRequestException("Oops! Cannot create test.");
    }
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<CostumerDto>> findAllTest() {
        try {
            List<CostumerDto> testDTOList = serviceTest.findAll();
            return new ResponseEntity<>(testDTOList, HttpStatus.OK);
        } catch (Exception e) {
            throw new ApiRequestException("Oops! Cannot get all data.");
        }
}
    @GetMapping("/findId/{id}")
    public ResponseEntity<CostumerDto> findTestById(@PathVariable Long id) {
        try {
        CostumerDto testDTO= serviceTest.findById(id);
        return new ResponseEntity<>(testDTO, HttpStatus.OK);
    } catch (Exception e) {
        throw new ApiRequestException("Oops! Cannot get test by id  .");
    }
    }
@PutMapping("/update/{id}")
    public ResponseEntity<CostumerDto> updateTest(@PathVariable Long id, @RequestBody CostumerDto testDTO) {
    try {
        testDTO.setOid(id);
        CostumerDto testDTO1= serviceTest.update(testDTO);
      return new ResponseEntity<>(testDTO1, HttpStatus.OK);
    } catch (Exception e) {
        throw new ApiRequestException("Oops! Cannot update test.");
       }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable Long id) {
try {
    serviceTest.delete(id);
    return new ResponseEntity<>("Test deleted", HttpStatus.OK);
}catch (ApiRequestException e) {
    throw new ApiRequestException("Oops! Cannot delete test.");
}
    }

}
