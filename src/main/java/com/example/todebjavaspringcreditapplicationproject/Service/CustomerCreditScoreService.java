package com.example.todebjavaspringcreditapplicationproject.Service;


import com.example.todebjavaspringcreditapplicationproject.Model.entity.Customer;
import com.example.todebjavaspringcreditapplicationproject.Repository.CustomerCreditScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static com.example.todebjavaspringcreditapplicationproject.Model.mapper.CreditScoreMapper.toCreditScore;
@Slf4j
@RequiredArgsConstructor
@Service
public class  CustomerCreditScoreService {

    private CustomerCreditScoreRepository creditScoreRepository;


    public void addScore(Customer customer) {
        int score= generateNumberBetween(1,1500);
        creditScoreRepository.save(toCreditScore(customer,score));
        log.info("Credit score generated for the customer.");
    }

    private int generateNumberBetween(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
