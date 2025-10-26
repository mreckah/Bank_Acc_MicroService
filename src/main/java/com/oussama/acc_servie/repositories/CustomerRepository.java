package com.oussama.acc_servie.repositories;

import com.oussama.acc_servie.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

