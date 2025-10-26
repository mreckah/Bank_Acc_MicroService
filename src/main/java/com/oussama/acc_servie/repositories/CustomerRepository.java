package com.oussama.acc_servie.repositories;

import com.oussama.acc_servie.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
