package org.izce.spring_mvc_rest.services;

import java.util.List;

import org.izce.spring_mvc_rest.api.v1.model.CategoryDTO;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}