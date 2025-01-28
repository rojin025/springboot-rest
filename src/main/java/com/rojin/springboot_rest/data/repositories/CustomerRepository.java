package com.rojin.springboot_rest.data.repositories;

import com.rojin.springboot_rest.data.entites.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);
}
