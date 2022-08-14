package com.example.todebjavaspringcreditapplicationproject.Service;


import com.example.todebjavaspringcreditapplicationproject.Model.dto.CreditApplicationDTO;
import com.example.todebjavaspringcreditapplicationproject.Model.entity.CreditApplication;
import com.example.todebjavaspringcreditapplicationproject.Model.entity.Customer;
import com.example.todebjavaspringcreditapplicationproject.Model.entity.CustomerCreditScore;
import com.example.todebjavaspringcreditapplicationproject.Repository.CreditApplicationRepository;
import com.example.todebjavaspringcreditapplicationproject.Repository.CustomerCreditScoreRepository;
import com.example.todebjavaspringcreditapplicationproject.Repository.CustomerRepository;
import com.example.todebjavaspringcreditapplicationproject.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

import static com.example.todebjavaspringcreditapplicationproject.Model.mapper.CreditApplicationMapper.toCreditApplication;


@Slf4j
@RequiredArgsConstructor
@Service
public class CreditApplicationService {
    private final int CREDIT_LIMIT_MULTIPLIER = 4;
    int creditScore, monthlyIncome, limit = 0;
    boolean isVerified;
    int checkNumber1 = 500, checkNumber2 = 1000;
    @Autowired
    private CreditApplicationRepository creditApplicationRepository;
    @Autowired
    private CustomerCreditScoreRepository creditScoreRepository;
    @Autowired
    private CustomerRepository customerRepository;
    //@Value //sms info message
    private String message;

    public void addCreditApplication(CreditApplicationDTO creditApplicationDTO) {
        Customer customer = customerRepository.findByNationalId(creditApplicationDTO.getNationalId());
        if (customer == null) {
            log.error("Customer not found with given identity number" + creditApplicationDTO.getNationalId());
            throw new NotFoundException("customer");
        }
        monthlyIncome = customer.getMonthlyIncome();

        CustomerCreditScore score = creditScoreRepository.findByCustomerNationalId(creditApplicationDTO.getNationalId());
        creditScore = score.getCreditScore();
        isVerified = checkIfAplicationIsApproved(creditScore, monthlyIncome);
        creditApplicationRepository.save(toCreditApplication(customer, limit, isVerified));
    }

    private boolean checkIfAplicationIsApproved(int creditScore, int monthlyIncome) {
        isVerified = true;

        if (creditScore < checkNumber1) {
            isVerified = false;
            log.error("Customer rejected from application request./checkIfAplicationIsApproved");
        } else if (creditScore > checkNumber1 && creditScore < checkNumber2 && monthlyIncome < 5000) {
            limit = 10000;
            log.info("Customer approved from application request.");
        } else if (creditScore > checkNumber1 && creditScore < checkNumber2 && monthlyIncome > 5000) {
            limit = 20000;
            log.info("Customer approved from application request.");
        } else if (creditScore >= checkNumber2) {
            limit = monthlyIncome * CREDIT_LIMIT_MULTIPLIER;
            log.info("Customer approved from application request.");
        }
        return isVerified;
    }


    public List<CreditApplication> getAllApplicationByCustomer(String nationalId) {
        Customer customer = customerRepository.findByNationalId(nationalId);
        if (customer == null)
            throw new NotFoundException("Customer of this national Id");
        return customer.getCreditApplications();
    }


    public String getResult(String nationalId) {
        Customer customer = customerRepository.findByNationalId(nationalId);
        Stream<CreditApplication> stream = customer.getCreditApplications().stream();
        long count = customer.getCreditApplications().stream().count();
        CreditApplication creditApplication = stream.skip(count - 1).findFirst()
                .orElseThrow(() -> new NotFoundException("Credit Application"));

        if (creditApplication.isApplicationApproved())
            return "Your credit application has been approved.Your credit limit is : " + getLimit(creditApplication)
                    + "." + message;
        else
            return "Your credit application has not been approved." + message;
    }


    public int getLimit(CreditApplication creditApplication) {
        return creditApplication.getCreditLimit();
    }

}
