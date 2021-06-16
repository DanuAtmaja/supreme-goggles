package com.laugh.membangunrestapi.models.repositories;

import com.laugh.membangunrestapi.models.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * this class using derived query
 */
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    List<Supplier> findByNameStartingWith(String prefix);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);
}
