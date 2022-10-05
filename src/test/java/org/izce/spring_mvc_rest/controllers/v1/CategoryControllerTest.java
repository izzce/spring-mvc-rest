package org.izce.spring_mvc_rest.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.izce.spring_mvc_rest.Constants.CATEGORY_CONTROLLER_BASE_URL;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.izce.spring_mvc_rest.api.v1.model.CategoryDTO;
import org.izce.spring_mvc_rest.controllers.RestResponseEntityExceptionHandler;
import org.izce.spring_mvc_rest.services.CategoryService;
import org.izce.spring_mvc_rest.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class CategoryControllerTest {

	public static final String NAME = "Jim";

	@Mock
	CategoryService categoryService;

	@InjectMocks
	CategoryController categoryController;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	public void testListCategories() throws Exception {
		CategoryDTO category1 = new CategoryDTO();
		category1.setId(1l);
		category1.setName(NAME);

		CategoryDTO category2 = new CategoryDTO();
		category2.setId(2l);
		category2.setName("Bob");

		List<CategoryDTO> categories = Arrays.asList(category1, category2);

		when(categoryService.getAllCategories()).thenReturn(categories);

		mockMvc.perform(get(CATEGORY_CONTROLLER_BASE_URL).contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.categories", hasSize(2)));
	}

	@Test
	public void testGetByNameCategories() throws Exception {
		CategoryDTO category1 = new CategoryDTO();
		category1.setId(1l);
		category1.setName(NAME);

		when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

		mockMvc.perform(get(CATEGORY_CONTROLLER_BASE_URL + "/Jim").contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", equalTo(NAME)));
	}

	@Test
	public void testGetByNameNotFound() throws Exception {

		when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(get(CATEGORY_CONTROLLER_BASE_URL + "/Foo").contentType(APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
