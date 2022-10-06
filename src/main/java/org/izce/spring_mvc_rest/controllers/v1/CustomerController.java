package org.izce.spring_mvc_rest.controllers.v1;

import static org.izce.spring_mvc_rest.Constants.CUSTOMER_CONTROLLER_BASE_URL;

import org.izce.spring_mvc_rest.api.v1.model.CustomerDTO;
import org.izce.spring_mvc_rest.api.v1.model.CustomerListDTO;
import org.izce.spring_mvc_rest.services.CustomerService;
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

@RestController
@RequestMapping(CUSTOMER_CONTROLLER_BASE_URL)
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO getListofCustomers() {
		return new CustomerListDTO(customerService.getAllCustomers());
	}

	@GetMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.createNewCustomer(customerDTO);
	}

	@PutMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		return customerService.saveCustomerByDTO(id, customerDTO);
	}

	@PatchMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		return customerService.patchCustomer(id, customerDTO);
	}

	@DeleteMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomerById(id);
	}
}

