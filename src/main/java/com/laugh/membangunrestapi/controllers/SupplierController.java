package com.laugh.membangunrestapi.controllers;

import com.laugh.membangunrestapi.dto.ResponseData;
import com.laugh.membangunrestapi.dto.SearchData;
import com.laugh.membangunrestapi.dto.SupplierData;
import com.laugh.membangunrestapi.models.entity.Supplier;
import com.laugh.membangunrestapi.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(ObjectError -> responseData.getMessages().add(ObjectError.getDefaultMessage()));
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

//        Supplier supplier = new Supplier();
//        supplier.setName(supplierData.getName());
//        supplier.setAddress(supplierData.getAddress());
//        supplier.setEmail(supplierData.getEmail());
//
        /**
         * automatis ngemapping. source to target. asal fieldnya sama
         */
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll(){
        return supplierService.findAll();
    }

    @GetMapping("/{id")
    public Supplier findOne(@PathVariable("id") Long id){
        return supplierService.findOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            errors.getAllErrors().forEach(ObjectError -> responseData.getMessages().add(ObjectError.getDefaultMessage()));
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/search/byemail")
    public Supplier findByEmail(@RequestBody SearchData searchData){
        return supplierService.findByEmail(searchData.getSearchKey());
    }

    @PostMapping("/search/byname")
    public List<Supplier> findByName(@RequestBody SearchData searchData){
        return supplierService.findByName(searchData.getSearchKey());
    }

    @PostMapping("/search/namestartwith")
    public List<Supplier> findByNameStartwith(@RequestBody SearchData searchData){
        return supplierService.findByNameStartWith(searchData.getSearchKey());
    }

    @PostMapping("/search/nameoremail")
    public List<Supplier> findByOrEmail(@RequestBody SearchData searchData){
        return supplierService.findByNameOrEmail(searchData.getSearchKey(), searchData.getOtherSearchKey());
    }

}
