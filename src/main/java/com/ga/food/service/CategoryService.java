package com.ga.food.service;

import com.ga.food.Exception.InfoExistException;
import com.ga.food.model.categories;
import com.ga.food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

//    @Autowired
//    public void setCategoryRepository(CategoryRepository categoryRepository){
//    this.categoryRepository = categoryRepository;
//    }

    //C-- Create -- HTTP POST - To create a record(category)
    @PostMapping("/categories")
    public categories createCategory(categories categoryObject) {
        System.out.println("Service Calling createCategory ===>");

        categories category = categoryRepository.findByName(categoryObject.getName());
        if (category != null) {
            throw new InfoExistException("category with name " + category.getName() + " already exists");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }
    //R-- Read -- HTTP GET - To read all record / record by id
    public List<categories> getCategories(){
        System.out.println("service calling category");
        return categoryRepository.findAll();}

    public categories getByIdCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));}


    //U -- Update -- HTTP PUT - To update a record
    public categories updateCategory(Long id, categories categoryObject) {

        categories category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryObject.getName());
        category.setDescription(categoryObject.getDescription());

        return categoryRepository.save(category);
    }

    //D -- Delete -- HTTP DELETE - To remove a record
    public void deleteCategory(Long id) {

        categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }
}
