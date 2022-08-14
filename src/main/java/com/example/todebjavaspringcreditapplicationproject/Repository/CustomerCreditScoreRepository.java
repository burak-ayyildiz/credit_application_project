package com.example.todebjavaspringcreditapplicationproject.Repository;

import com.example.todebjavaspringcreditapplicationproject.Model.entity.CustomerCreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerCreditScoreRepository extends JpaRepository<CustomerCreditScore, Integer> {
    CustomerCreditScore findByCustomerNationalId(String tckn);
}
