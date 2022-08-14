package com.example.todebjavaspringcreditapplicationproject.Controller;

import com.example.todebjavaspringcreditapplicationproject.Model.entity.Customer;
import com.example.todebjavaspringcreditapplicationproject.Service.CustomerCreditScoreService;
import com.example.todebjavaspringcreditapplicationproject.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerCreditScoreService creditScoreService;



    @PostMapping(value = "/create")
    public void createCustomer(@Valid @RequestBody Customer customer) {
        log.info("Customer created.");
        customerService.addCustomer(customer);
    }

    @PutMapping(value = "/update")
    public Customer updateCustomer(@Valid @RequestBody Customer customer) {
        log.info("Customer updated.");
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteCustomer(@RequestParam @Min(1) Integer id) {
        log.info("Customer deleted.");
        return customerService.deleteCustomer(id);
    }
}