package org.izce.spring_mvc_rest.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.izce.spring_mvc_rest.api.v1.mapper.VendorMapper;
import org.izce.spring_mvc_rest.api.v1.model.VendorDTO;
import org.izce.spring_mvc_rest.domain.Vendor;
import org.izce.spring_mvc_rest.repo.VendorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VendorServiceTest {

    @Mock
    VendorRepo vendorRepo;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    VendorService vendorService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(vendorMapper, vendorRepo);
    }

    @Test
    public void getAllVendors() throws Exception {
        //given
        Vendor vendor1 = new Vendor();
        vendor1.setId(1l);
        vendor1.setName("Izce Fresh Fruits");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2l);
        vendor2.setName("Tema Shop");

        when(vendorRepo.findAll()).thenReturn(List.of(vendor1, vendor2));

        //when
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        //then
        assertEquals(2, vendorDTOS.size());

    }

    @Test
    public void getVendorById() throws Exception {
        //given
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Izce Fresh Fruits");

        when(vendorRepo.findById(anyLong())).thenReturn(Optional.of(vendor1));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        assertEquals("Izce Fresh Fruits", vendorDTO.getName());
    }

    @Test
    public void createNewVendor() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Tema Shop");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(2l);

        when(vendorRepo.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/vendors/2", savedDto.getVendorUrl());
    }

    @Test
    public void saveVendorByDTO() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Tema Shop");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(3l);

        when(vendorRepo.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.saveVendorByDTO(3L, vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/vendors/3", savedDto.getVendorUrl());
    }

    @Test
    public void deleteVendorById() throws Exception {
        Long id = 1L;

        vendorRepo.deleteById(id);

        verify(vendorRepo, times(1)).deleteById(anyLong());
    }

}