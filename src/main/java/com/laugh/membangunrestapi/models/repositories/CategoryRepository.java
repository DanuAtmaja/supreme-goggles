package com.laugh.membangunrestapi.models.repositories;

import com.laugh.membangunrestapi.models.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * using Paging and Sorting repository
 */
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    Page<Category> findByNameContains(String name, Pageable pageable);

}
