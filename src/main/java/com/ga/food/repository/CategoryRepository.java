package com.ga.food.repository;

import com.ga.food.model.categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<categories, Long> {
    categories findByName(String categoryName);
    categories findByNameAndDescription(String name ,String description);
    categories findByUserIdAndName(Long userId , String categoryName);
    List<categories> findByUserId(Long userId);
}


