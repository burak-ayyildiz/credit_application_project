package com.example.todebjavaspringcreditapplicationproject.Service;

import com.example.todebjavaspringcreditapplicationproject.Model.entity.Customer;
import com.example.todebjavaspringcreditapplicationproject.Repository.CustomerRepository;
import com.example.todebjavaspringcreditapplicationproject.exception.NationalIdAlreadyExistsException;
import com.example.todebjavaspringcreditapplicationproject.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerCreditScoreService creditScoreService;


    public List<Customer> getAllCustomer() {
        log.info("Customer getall request");
        return customerRepository.findAll();
    }


    public Customer getCustomer(Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        log.info("Customer getby id request");
        return byId.orElseThrow(() -> new NotFoundException("Customer"));
    }


    public void addCustomer(Customer customer) {
        if (customerRepository.findByNationalId(customer.getNationalId())!=null) {
            log.error("Customer not found by id request");
            throw new NationalIdAlreadyExistsException();
        }
        customerRepository.save(customer);
        creditScoreService.addScore(customer);

    }


    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);

    }


    public boolean deleteCustomer(Integer id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer"));
        customerRepository.delete(getCustomer(id));
        return true;
    }
}