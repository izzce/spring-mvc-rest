package org.izce.spring_mvc_rest.api.v1.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorListDTO {
	@Schema(description = "List of vendors", required = true)
    List<VendorDTO> vendors;
}