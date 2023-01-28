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
public class VendorDTO {

	@Schema(description = "The vendor name", required = true)
    private String name;

	@Schema(accessMode = AccessMode.READ_ONLY)
    @JsonProperty("vendor_url")
    private String vendorUrl;
}