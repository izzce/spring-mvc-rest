package org.izce.spring_mvc_rest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.izce.spring_mvc_rest.api.v1.model.CategoryDTO;
import org.izce.spring_mvc_rest.domain.Category;
import org.junit.jupiter.api.Test;

class CategoryMapperTest {

	public static final String NAME = "Joe";
	public static final Long ID = 1L;

	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

	@Test
	public void categoryToCategoryDTO() throws Exception {

		// given
		Category category = new Category();
		category.setName(NAME);
		category.setId(ID);

		// when
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

		// then
		assertEquals(ID, categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}
}
