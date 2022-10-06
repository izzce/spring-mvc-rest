package org.izce.spring_mvc_rest.repo;

import org.izce.spring_mvc_rest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<Vendor, Long>{
}