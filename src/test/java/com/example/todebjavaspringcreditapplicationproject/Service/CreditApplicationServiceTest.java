package com.example.todebjavaspringcreditapplicationproject.Service;


import com.example.todebjavaspringcreditapplicationproject.Model.entity.CreditApplication;
import com.example.todebjavaspringcreditapplicationproject.Model.entity.Customer;
import com.example.todebjavaspringcreditapplicationproject.Repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CreditApplicationService creditApplicationService;

    @Test
    void getAllApplicationByCustomer_successful() {


        Customer customer = new Customer(11, "12345678901", "John", "Stapp", 3000, "05432109876", new ArrayList<>());
        CreditApplication application1 = new CreditApplication(1, customer, false, 0, new Date());

        CreditApplication application2 = new CreditApplication(2, customer, false, 0, new Date());
        Customer updatedCustomer = new Customer(11, "12345678901", "John", "Stapp", 3000, "05432109876", Arrays.asList(application1, application2));

        List<CreditApplication> expectedApplications = new ArrayList<>();
        expectedApplications.add(application1);
        expectedApplications.add(application2);

        // When
        when(customerRepository.findByNationalId("12345678901")).thenReturn(updatedCustomer);
        // Then
        List<CreditApplication> allApplications = creditApplicationService.getAllApplicationByCustomer(updatedCustomer.getNationalId());

        Assert.assertEquals(expectedApplications.size(), allApplications.size());
    }
}