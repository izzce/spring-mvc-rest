package org.izce.spring_mvc_rest.controllers.v1;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.izce.spring_mvc_rest.Constants.VENDOR_CONTROLLER_BASE_URL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.izce.spring_mvc_rest.api.v1.model.VendorDTO;
import org.izce.spring_mvc_rest.services.ResourceNotFoundException;
import org.izce.spring_mvc_rest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VendorController.class)
public class VendorControllerTest extends AbstractRestControllerTest {

    @MockBean
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc;

    VendorDTO vendor1, vendor2;
    
    @BeforeEach
    public void setUp() throws Exception {
    	 vendor1 = new VendorDTO("Michale", VENDOR_CONTROLLER_BASE_URL + "/1");
    	 vendor2 = new VendorDTO("Sam", VENDOR_CONTROLLER_BASE_URL + "/2");
    }

    @Test
    public void testListVendors() throws Exception {

        //given
        when(vendorService.getAllVendors()).thenReturn(List.of(vendor1, vendor2));

        mockMvc.perform(get(VENDOR_CONTROLLER_BASE_URL)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetVendorById() throws Exception {

        //given
        when(vendorService.getVendorById(anyLong())).thenReturn(vendor1);

        //when
        mockMvc.perform(get(VENDOR_CONTROLLER_BASE_URL + "/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Michale")));
    }

    @Test
    public void createNewVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Fred");

        VendorDTO returnDTO = new VendorDTO(vendor.getName(), VENDOR_CONTROLLER_BASE_URL + "/1");

        when(vendorService.createNewVendor(vendor)).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(post(VENDOR_CONTROLLER_BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Fred")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VENDOR_CONTROLLER_BASE_URL + "/1")));
    }

    @Test
    public void testUpdateVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Fred");

        VendorDTO returnDTO = new VendorDTO(vendor.getName(), VENDOR_CONTROLLER_BASE_URL + "/1");

        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put(VENDOR_CONTROLLER_BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Fred")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VENDOR_CONTROLLER_BASE_URL + "/1")));
    }

    @Test
    public void testPatchVendor() throws Exception {

        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Fred");

        VendorDTO returnDTO = new VendorDTO(vendor.getName(), VENDOR_CONTROLLER_BASE_URL + "/1");

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(VENDOR_CONTROLLER_BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Fred")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VENDOR_CONTROLLER_BASE_URL + "/1")));
    }

    @Test
    public void testDeleteVendor() throws Exception {

        mockMvc.perform(delete(VENDOR_CONTROLLER_BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(VENDOR_CONTROLLER_BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}