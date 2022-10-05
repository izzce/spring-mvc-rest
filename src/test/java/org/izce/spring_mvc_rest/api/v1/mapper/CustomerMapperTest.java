package org.izce.spring_mvc_rest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.izce.spring_mvc_rest.api.v1.model.CustomerDTO;
import org.izce.spring_mvc_rest.domain.Customer;
import org.junit.jupiter.api.Test;

public class CustomerMapperTest {

	public static final String FIRSTNAME = "Jimmy";
	public static final String LASTNAME = "Fallon";
	
	CustomerMapper customerMapper = CustomerMapper.INSTANCE;

	@Test
	public void customerToCustomerDTO() throws Exception {
		// given
		Customer customer = new Customer();
		customer.setFirstname(FIRSTNAME);
		customer.setLastname(LASTNAME);

		// when
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

		// then
		assertEquals(FIRSTNAME, customerDTO.getFirstname());
		assertEquals(LASTNAME, customerDTO.getLastname());

	}

}