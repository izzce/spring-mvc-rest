package org.izce.spring_mvc_rest.services;

import static org.izce.spring_mvc_rest.Constants.VENDOR_CONTROLLER_BASE_URL;

import java.util.List;
import java.util.stream.Collectors;

import org.izce.spring_mvc_rest.api.v1.mapper.VendorMapper;
import org.izce.spring_mvc_rest.api.v1.model.VendorDTO;
import org.izce.spring_mvc_rest.domain.Vendor;
import org.izce.spring_mvc_rest.repo.VendorRepo;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

	private final VendorMapper vendorMapper;
	private final VendorRepo vendorRepo;

	public VendorServiceImpl(VendorMapper vendorMapper, VendorRepo vendorRepo) {
		this.vendorMapper = vendorMapper;
		this.vendorRepo = vendorRepo;
	}

	@Override
	public List<VendorDTO> getAllVendors() {
		return vendorRepo.findAll().stream().map(vendor -> {
			VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
			vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
			return vendorDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public VendorDTO getVendorById(Long id) {

		return vendorRepo.findById(id).map(vendorMapper::vendorToVendorDTO).map(vendorDTO -> {
			// set API URL
			vendorDTO.setVendorUrl(getVendorUrl(id));
			return vendorDTO;
		}).orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) {

		return saveAndReturnDTO(vendorMapper.vendorDtoToVendor(vendorDTO));
	}

	private VendorDTO saveAndReturnDTO(Vendor vendor) {
		Vendor savedVendor = vendorRepo.save(vendor);

		VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);

		returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

		return returnDto;
	}

	@Override
	public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
		Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
		vendor.setId(id);

		return saveAndReturnDTO(vendor);
	}

	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
		return vendorRepo.findById(id).map(vendor -> {

			if (vendorDTO.getName() != null) {
				vendor.setName(vendorDTO.getName());
			}

			VendorDTO returnDto = vendorMapper.vendorToVendorDTO(vendorRepo.save(vendor));

			returnDto.setVendorUrl(getVendorUrl(id));

			return returnDto;

		}).orElseThrow(ResourceNotFoundException::new);
	}

	private String getVendorUrl(Long id) {
		return VENDOR_CONTROLLER_BASE_URL + "/" + id;
	}

	@Override
	public void deleteVendorById(Long id) {
		vendorRepo.deleteById(id);
	}
}