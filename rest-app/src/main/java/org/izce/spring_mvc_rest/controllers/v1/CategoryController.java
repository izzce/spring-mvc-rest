package org.izce.spring_mvc_rest.controllers.v1;

import static org.izce.spring_mvc_rest.Constants.CATEGORY_CONTROLLER_BASE_URL;

import org.izce.spring_mvc_rest.api.v1.model.CategoryDTO;
import org.izce.spring_mvc_rest.api.v1.model.CategoryListDTO;
import org.izce.spring_mvc_rest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(CATEGORY_CONTROLLER_BASE_URL)
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Lists all available categories.")
	public CategoryListDTO getAllCategories() {
		return new CategoryListDTO(categoryService.getAllCategories());
	}

	@GetMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get a category with its name.")
	public CategoryDTO getCategoryByName(@PathVariable String name) {
		return categoryService.getCategoryByName(name);
	}
}
