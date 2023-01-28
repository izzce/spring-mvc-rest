package org.izce.spring_mvc_rest.services;

import static org.izce.spring_mvc_rest.Constants.CUSTOMER_CONTROLLER_BASE_URL;

import java.util.List;
import java.util.stream.Collectors;

import org.izce.spring_mvc_rest.api.v1.mapper.CustomerMapper;
import org.izce.spring_mvc_rest.api.v1.model.CustomerDTO;
import org.izce.spring_mvc_rest.domain.Customer;
import org.izce.spring_mvc_rest.repo.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepo customerRepo) {
        this.customerMapper = customerMapper;
        this.customerRepo = customerRepo;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo
                .findAll()
                .stream()
                .map(customer -> {
                   CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                   customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                   return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerRepo.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    //set API URL
                    customerDTO.setCustomerUrl(getCustomerUrl(id));
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepo.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnDto.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnDto;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepo.findById(id).map(customer -> {

            if(customerDTO.getFirstname() != null){
                customer.setFirstname(customerDTO.getFirstname());
            }

            if(customerDTO.getLastname() != null){
                customer.setLastname(customerDTO.getLastname());
            }

            CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customerRepo.save(customer));

            returnDto.setCustomerUrl(getCustomerUrl(id));

            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    private String getCustomerUrl(Long id) {
        return CUSTOMER_CONTROLLER_BASE_URL + "/" + id;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepo.deleteById(id);
    }
}