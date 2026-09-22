package com.ga.food.controller;

import com.ga.food.Exception.InfoExistException;
import com.ga.food.model.categories;
import com.ga.food.service.*;
import com.ga.food.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    @Autowired
//    public void setCategoryService(CategoryService categoryService){
//        this.categoryService=categoryService;
//    }

    //CRUD
    //C-- Create -- HTTP POST - To create a record(category)
    @PostMapping("/categories")
    public categories createCategory(@RequestBody categories categoryObject) {
        System.out.println("Service Calling createCategory ===>");
        return categoryService.createCategory(categoryObject);
    }


        //R-- Read -- HTTP GET - To read all record / record by id
        @GetMapping("/categories")
        public List<categories> getCategories() {
            System.out.println("calling category");
            return categoryService.getCategories();
        }

    @GetMapping("/categories/{id}")
    public categories getCategory(@PathVariable Long id) {
        System.out.println("calling one category");
        return categoryService.getByIdCategory(id);
    }

        //U -- Update -- HTTP PUT - To update a record
        @PutMapping("/categories/{id}")
        public categories updateCategory(
                @PathVariable Long id,
                @RequestBody categories categoryObject) {

            return categoryService.updateCategory(id, categoryObject);
        }


        //D -- Delete -- HTTP DELETE - To remove a record
        @DeleteMapping("/categories/{id}")
        public void deleteCategory(@PathVariable Long id) {

            categoryService.deleteCategory(id);
        }

}