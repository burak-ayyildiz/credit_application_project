package com.example.todebjavaspringcreditapplicationproject.Model.mapper;

import com.example.todebjavaspringcreditapplicationproject.Model.dto.CreditApplicationDTO;
import com.example.todebjavaspringcreditapplicationproject.Model.entity.CreditApplication;
import com.example.todebjavaspringcreditapplicationproject.Model.entity.Customer;

import java.util.Calendar;

public class CreditApplicationMapper {
    public static CreditApplication toCreditApplication(Customer customer, int limit, boolean isApplicationApproved)
    {
        CreditApplication creditApplication= new CreditApplication();
        creditApplication.setCreditLimit(limit);
        creditApplication.setApplicationApproved(isApplicationApproved);
        creditApplication.setCustomer(customer);
        creditApplication.setApplyDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        return creditApplication;
    }
}
