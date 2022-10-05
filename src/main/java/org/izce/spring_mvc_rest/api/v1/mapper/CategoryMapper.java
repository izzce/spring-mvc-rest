package org.izce.spring_mvc_rest.api.v1.mapper;

import org.izce.spring_mvc_rest.api.v1.model.CategoryDTO;
import org.izce.spring_mvc_rest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
