package com.rojin.springboot_rest.web.rest;

import com.rojin.springboot_rest.data.services.CustomerService;
import com.rojin.springboot_rest.web.models.Customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(@RequestParam(name = "email", required = false)String email){
        return  this.customerService.getCustomers(email);
    }
}
