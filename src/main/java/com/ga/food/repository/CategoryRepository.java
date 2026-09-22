package com.ga.food.repository;

import com.ga.food.model.categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepository extends JpaRepository<categories, Long> {
    categories findByName(String categoryName);
    categories findByNameAndDescription(String name ,String description);
}


