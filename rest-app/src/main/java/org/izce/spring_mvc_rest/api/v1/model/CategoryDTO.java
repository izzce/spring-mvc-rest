package org.izce.spring_mvc_rest.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    @Schema(description = "Category name", required = true)
    private String name;
}