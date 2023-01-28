package org.izce.spring_mvc_rest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.izce.spring_mvc_rest.api.v1.model.VendorDTO;
import org.izce.spring_mvc_rest.domain.Vendor;
import org.junit.jupiter.api.Test;

public class VendorMapperTest {

	public static final String NAME = "Tema Shop";
	
	VendorMapper vendorMapper = VendorMapper.INSTANCE;

	@Test
	public void vendorToVendorDTO() throws Exception {
		// given
		Vendor vendor = new Vendor();
		vendor.setName(NAME);

		// when
		VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

		// then
		assertEquals(NAME, vendorDTO.getName());
	}

}