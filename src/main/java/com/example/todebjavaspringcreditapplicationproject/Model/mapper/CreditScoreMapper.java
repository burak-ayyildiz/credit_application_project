package com.example.todebjavaspringcreditapplicationproject.Model.mapper;


import com.example.todebjavaspringcreditapplicationproject.Model.entity.Customer;
import com.example.todebjavaspringcreditapplicationproject.Model.entity.CustomerCreditScore;

public class CreditScoreMapper {
    public static CustomerCreditScore toCreditScore(Customer customer, int score) {
        CustomerCreditScore creditScore = new CustomerCreditScore();
        creditScore.setCustomer(customer);
        creditScore.setCreditScore(score);
        return creditScore;
    }
}
