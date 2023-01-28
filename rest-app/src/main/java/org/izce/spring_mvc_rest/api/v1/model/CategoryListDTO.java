package org.izce.spring_mvc_rest.api.v1.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryListDTO {
	@Schema(description = "List of categories", required = true)
    List<CategoryDTO> categories;

}