package com.oussama.acc_servie.repositories;

import com.oussama.acc_servie.entities.Customer;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
