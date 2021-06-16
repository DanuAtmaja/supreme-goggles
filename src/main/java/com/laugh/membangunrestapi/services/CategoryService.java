package com.laugh.membangunrestapi.services;

import com.laugh.membangunrestapi.models.entity.Category;
import com.laugh.membangunrestapi.models.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.Optional;

@Service
@TransactionScoped
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveOrUpdate(Category category){
        return categoryRepository.save(category);
    }

    public Category findOne(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            return null;
        }
        return category.get();
    }

    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void removeOne(Long id){
        categoryRepository.deleteById(id);
    }

    public Iterable<Category> findByName(String name, Pageable pageable){
        return categoryRepository.findByNameContains(name,pageable);
    }

    public Iterable<Category> saveBatch(Iterable<Category> categories){
        return categoryRepository.saveAll(categories);
    }
}
