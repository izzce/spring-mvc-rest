package org.izce.spring_mvc_rest.controllers.v1;

import static org.izce.spring_mvc_rest.Constants.CATEGORY_CONTROLLER_BASE_URL;

import org.izce.spring_mvc_rest.api.v1.model.CategoryDTO;
import org.izce.spring_mvc_rest.api.v1.model.CategoryListDTO;
import org.izce.spring_mvc_rest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CATEGORY_CONTROLLER_BASE_URL)
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<CategoryListDTO> getAllCategories() {

		return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()),
				HttpStatus.OK);
	}

	@GetMapping("{name}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
		return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByName(name), HttpStatus.OK);
	}
}

