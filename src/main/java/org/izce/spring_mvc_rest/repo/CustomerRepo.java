package org.izce.spring_mvc_rest.repo;

import org.izce.spring_mvc_rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
}