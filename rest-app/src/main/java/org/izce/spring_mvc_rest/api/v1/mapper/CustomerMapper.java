package org.izce.spring_mvc_rest.api.v1.mapper;

import org.izce.spring_mvc_rest.api.v1.model.CustomerDTO;
import org.izce.spring_mvc_rest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}