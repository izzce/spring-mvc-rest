package org.izce.spring_mvc_rest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.izce.spring_mvc_rest.api.v1.mapper.CustomerMapper;
import org.izce.spring_mvc_rest.api.v1.model.CustomerDTO;
import org.izce.spring_mvc_rest.bootstrap.DataLoader;
import org.izce.spring_mvc_rest.domain.Customer;
import org.izce.spring_mvc_rest.repo.CategoryRepo;
import org.izce.spring_mvc_rest.repo.CustomerRepo;
import org.izce.spring_mvc_rest.repo.VendorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceIT {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	VendorRepo vendorRepo;


	CustomerService customerService;

	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("Loading Customer Data");
		System.out.println(customerRepo.findAll().size());

		// setup data for testing
		DataLoader dataLoader = new DataLoader(categoryRepo, customerRepo, vendorRepo);
		dataLoader.run(); // load data

		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepo);
	}

	@Test
	public void patchCustomerUpdateFirstName() throws Exception {
		String updatedName = "UpdatedName";
		Long id = getCustomerIdValue();

		Customer originalCustomer = customerRepo.getReferenceById(id);
		assertNotNull(originalCustomer);
		// save original first name
		String originalFirstName = originalCustomer.getFirstname();
		String originalLastName = originalCustomer.getLastname();

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstname(updatedName);

		customerService.patchCustomer(id, customerDTO);

		Customer updatedCustomer = customerRepo.findById(id).get();

		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getFirstname());
		assertNotEquals(originalFirstName, updatedCustomer.getFirstname());
		assertEquals(originalLastName, updatedCustomer.getLastname());
	}

	@Test
	public void patchCustomerUpdateLastName() throws Exception {
		String updatedName = "UpdatedName";
		long id = getCustomerIdValue();

		Customer originalCustomer = customerRepo.getReferenceById(id);
		assertNotNull(originalCustomer);

		// save original first/last name
		String originalFirstName = originalCustomer.getFirstname();
		String originalLastName = originalCustomer.getLastname();

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastname(updatedName);

		customerService.patchCustomer(id, customerDTO);

		Customer updatedCustomer = customerRepo.findById(id).get();

		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getLastname());
		assertEquals(originalFirstName, updatedCustomer.getFirstname());
		assertNotEquals(originalLastName, updatedCustomer.getLastname());
	}

	private Long getCustomerIdValue() {
		List<Customer> customers = customerRepo.findAll();

		System.out.println("Customers Found: " + customers.size());

		// return first id
		return customers.get(0).getId();
	}
}