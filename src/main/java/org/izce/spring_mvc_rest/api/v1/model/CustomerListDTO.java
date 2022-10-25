package org.izce.spring_mvc_rest.api.v1.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDTO {
	@Schema(description = "List of Customers", accessMode = AccessMode.READ_ONLY)
    List<CustomerDTO> customers;
}