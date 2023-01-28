package org.izce.spring_mvc_rest.controllers.v1;

import static org.izce.spring_mvc_rest.Constants.VENDOR_CONTROLLER_BASE_URL;

import org.izce.spring_mvc_rest.api.v1.model.VendorDTO;
import org.izce.spring_mvc_rest.api.v1.model.VendorListDTO;
import org.izce.spring_mvc_rest.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vendor Controller", description = "This is Izce's Vendor Controller REST API.")
@RestController
@RequestMapping(VENDOR_CONTROLLER_BASE_URL)
public class VendorController {

	private final VendorService vendorService;

	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Lists all available vendors.")
	public VendorListDTO getListofVendors() {
		return new VendorListDTO(vendorService.getAllVendors());
	}

	@GetMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get a vendor with its id.")
	public VendorDTO getVendorById(@PathVariable Long id) {
		return vendorService.getVendorById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Create a new vendor.")
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
		return vendorService.createNewVendor(vendorDTO);
	}

	@PutMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Update a vendor name with its id.")
	public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
		return vendorService.saveVendorByDTO(id, vendorDTO);
	}

	@PatchMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Patch a vendor with its id.")
	public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
		return vendorService.patchVendor(id, vendorDTO);
	}

	@DeleteMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Delete a vendor with its id.")
	public void deleteVendor(@PathVariable Long id) {
		vendorService.deleteVendorById(id);
	}
}

