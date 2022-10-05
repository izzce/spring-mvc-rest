package org.izce.spring_mvc_rest.repo;

import org.izce.spring_mvc_rest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Category findByName(String name);

}