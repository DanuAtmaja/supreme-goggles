package com.laugh.membangunrestapi.services;

import com.laugh.membangunrestapi.models.entity.Product;
import com.laugh.membangunrestapi.models.entity.Supplier;
import com.laugh.membangunrestapi.models.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class generally used for business logic
 */
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    public Product saveOrUpdate(Product product){
        return productRepository.save(product);
    }

    public Product findOne(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()){
            return null;
        }
        return productRepository.findById(id).get();
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public void removeOne(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> findByName(String name){
        return productRepository.findByNameContains(name);
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findOne(productId);
        if (product == null){
            throw new RuntimeException("Product with ID: "+productId+" not foudn");
        }
        product.getSuppliers().add(supplier);
        saveOrUpdate(product);
    }

    public Product findByProductName(String name){
        return productRepository.findProductByName(name);
    }

    public List<Product> findByProductNameLike(String name){
        return productRepository.findProductByNameLike("%"+name+"%");
    }

    public List<Product> findByCategory(Long categoryId){
        return productRepository.findProductByCategory(categoryId);
    }

    public List<Product> findBySupplier(Long supplierId){
        Supplier supplier = supplierService.findOne(supplierId);
        if (supplier == null){
            return new ArrayList<Product>();
        }
        return productRepository.findProductBySuppliers(supplier);
    }
}
