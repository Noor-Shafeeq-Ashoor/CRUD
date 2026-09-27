package com.ga.food.service;

import com.ga.food.Exception.InfoExistException;
import com.ga.food.Exception.InfoNotFoundException;
import com.ga.food.model.categories;
import com.ga.food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

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
    public categories createCategory(String name, String description, MultipartFile image) {
        categories category = categoryRepository.findByName(name);
        if (category != null) {
            throw new InfoExistException("category with name " + category.getName() + " already exists");
        }

        try {
            // Create upload directory
            Path uploadDirectory = Paths.get("uploads/categories");

            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }

            // Get original image name
            String fileName = image.getOriginalFilename();

            // Save image
            Path imagePath = uploadDirectory.resolve(fileName);

            Files.copy(
                    image.getInputStream(),
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            // Create category
            category = new categories();

            category.setName(name);
            category.setDescription(description);
            category.setImageURL("/uploads/categories/" + fileName);

            return categoryRepository.save(category);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    //R-- Read -- HTTP GET - To read all record / record by id
    public List<categories> getCategories(){
        System.out.println("service calling category");
        return categoryRepository.findAll();}

    public categories getByIdCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));}


    //U -- Update -- HTTP PUT - To update a record
    public categories updateCategory(Long categoryId, categories categoryObject) {
        System.out.println("service calling updateCategory ==>");
        Optional<categories> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            if (categoryObject.getName().equals(category.get().getName())) {
                System.out.println("Same");
                throw new InfoExistException("category " + category.get().getName() + " is already exists");
            } else {
                categories updateCategory = categoryRepository.findById(categoryId).get();
                updateCategory.setName(categoryObject.getName());
                updateCategory.setDescription(categoryObject.getDescription());
                return categoryRepository.save(updateCategory);
            }
        } else {
            throw new InfoNotFoundException("category with id " + categoryId + " not found");
        }
    }



    //D -- Delete -- HTTP DELETE - To remove a record
    public Optional<categories> deleteCategory(Long categoryId) {
        System.out.println("service calling deleteCategory ==>");
        Optional<categories> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return category;
        } else {
            throw new InfoNotFoundException("category with id " + categoryId + " not found");
        }
    }
}
