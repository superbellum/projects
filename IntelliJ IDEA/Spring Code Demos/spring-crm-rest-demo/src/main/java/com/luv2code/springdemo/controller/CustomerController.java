package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController
{
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    // LIST
    @GetMapping("/customers")
    public List<Customer> getCustomers()
    {
        return customerService.getCustomers();
    }

    // READ
    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id)
    {
        Customer customer = customerService.getCustomer(id);

        return customer;
    }

    // CREATE
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer)
    {
        customer.setId(0);

        customerService.saveCustomer(customer);

        return customer;
    }

    // UPDATE
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer)
    {
        customerService.saveCustomer(customer);

        return customer;
    }

    // DELETE
    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id)
    {
        customerService.deleteCustomer(id);

        return "Deleted customer: " + id;
    }
}
