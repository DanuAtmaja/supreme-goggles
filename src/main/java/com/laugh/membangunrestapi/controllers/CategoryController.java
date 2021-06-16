package com.laugh.membangunrestapi.controllers;

import com.laugh.membangunrestapi.dto.CategoryData;
import com.laugh.membangunrestapi.dto.ResponseData;
import com.laugh.membangunrestapi.dto.SearchData;
import com.laugh.membangunrestapi.models.entity.Category;
import com.laugh.membangunrestapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.Arrays;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            errors.getAllErrors().forEach(ObjectError -> responseData.getMessages().add(ObjectError.getDefaultMessage()));
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.saveOrUpdate(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id){
        return categoryService.findOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            errors.getAllErrors().forEach(ObjectError -> responseData.getMessages().add(ObjectError.getDefaultMessage()));
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.saveOrUpdate(category));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("search/{size}/{page}")
    public Iterable<Category> findByName(@RequestBody SearchData searchData, @PathVariable("size") Integer size,
                                         @PathVariable("page") Integer page){
        PageRequest pageable = PageRequest.of(size,page);
        return categoryService.findByName(searchData.getSearchKey(),pageable);
    }

    @PostMapping("search/{size}/{page}/{sort}")
    public Iterable<Category> findByName(@RequestBody SearchData searchData, @PathVariable("size") Integer size,
                                         @PathVariable("page") Integer page, @PathVariable("sort") String sort){
        PageRequest pageable = PageRequest.of(size,page, Sort.by("id"));
        if (sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page,size, Sort.by("id").descending());
        }
        return categoryService.findByName(searchData.getSearchKey(),pageable);
    }

    @PostMapping("/batch")
    public ResponseEntity<ResponseData<Iterable<Category>>> createBatch(@RequestBody Category[] categories){

        ResponseData<Iterable<Category>> responseData = new ResponseData<>();
        responseData.setPayload(categoryService.saveBatch(Arrays.asList(categories)));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }
}
