package org.izce.spring_mvc_rest.api.v1.mapper;

import org.izce.spring_mvc_rest.api.v1.model.VendorDTO;
import org.izce.spring_mvc_rest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}