package org.izce.spring_mvc_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.izce.spring_mvc_rest.api.v1.mapper.CategoryMapper;
import org.izce.spring_mvc_rest.api.v1.model.CategoryDTO;
import org.izce.spring_mvc_rest.repo.CategoryRepo;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepo categoryRepo) {
        this.categoryMapper = categoryMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return categoryRepo.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepo.findByName(name));
    }
}