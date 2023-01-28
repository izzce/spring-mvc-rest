package org.izce.spring_mvc_rest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	@Schema(description = "Customer's first name", required = true)
    private String firstname;
	@Schema(description = "Customer's last name", required = true)
    private String lastname;

	@Schema(accessMode = AccessMode.READ_ONLY)
    @JsonProperty("customer_url")
    private String customerUrl;
}