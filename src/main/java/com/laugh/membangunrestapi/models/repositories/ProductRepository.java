package com.laugh.membangunrestapi.models.repositories;

import com.laugh.membangunrestapi.models.entity.Product;
import com.laugh.membangunrestapi.models.entity.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * This function is for giving action to entity
 * This class using jpa query
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameContains(String name);

    @Query("SELECT p from Product p WHERE p.name = :name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product  p WHERE p.name like :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySuppliers(@PathParam("supplier") Supplier supplier);
}
