package com.rojin.springboot_rest.data.services;

import com.rojin.springboot_rest.data.entites.CustomerEntity;
import com.rojin.springboot_rest.data.repositories.CustomerRepository;
import com.rojin.springboot_rest.web.errors.NotFoundException;
import com.rojin.springboot_rest.web.models.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository= customerRepository;
    }

    public List<Customer> getCustomers(String filterEmail){
        List<Customer> customers = new ArrayList<>();

        if(StringUtils.hasLength(filterEmail)){
            CustomerEntity entity = this.customerRepository.findByEmail(filterEmail);
            customers.add(this.translateDbToWeb(entity));
        } else {
            Iterable<CustomerEntity> entities = this.customerRepository.findAll();
            entities.forEach(entity->{
                customers.add(this.translateDbToWeb(entity));
            });
        }

        return  customers;
    }

    public Customer getCustomer(long id){
        Optional<CustomerEntity> optional = this.customerRepository.findById(id);

        if(optional.isEmpty()){
            throw new NotFoundException("Not found with id " + id);
        }

        return this.translateDbToWeb(optional.get());
    }

    public Customer createOrUpdate(Customer customer){
        CustomerEntity entity = this.translateWebToDb(customer);
        entity = this.customerRepository.save(entity);

        return this.translateDbToWeb(entity);
    }

    public void deleteCustomer(long id){
        this.customerRepository.deleteById(id);
    }


    private CustomerEntity translateWebToDb(Customer customer){
        CustomerEntity entity = new CustomerEntity();

        entity.setId(customer.getCustomerId());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmail());
        entity.setPhone(customer.getPhone());
        entity.setAddress(customer.getAddress());

        return entity;
    }

    private Customer translateDbToWeb(CustomerEntity entity){
        return new Customer(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress()
        );
    }
}
