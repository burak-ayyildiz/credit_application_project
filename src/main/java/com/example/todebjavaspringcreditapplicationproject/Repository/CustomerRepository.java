package com.example.todebjavaspringcreditapplicationproject.Repository;

import com.example.todebjavaspringcreditapplicationproject.Model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByNationalId(String nationalId);
}
