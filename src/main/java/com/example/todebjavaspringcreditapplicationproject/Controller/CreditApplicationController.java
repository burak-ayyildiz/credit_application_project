package com.example.todebjavaspringcreditapplicationproject.Controller;

import com.example.todebjavaspringcreditapplicationproject.Model.dto.CreditApplicationDTO;
import com.example.todebjavaspringcreditapplicationproject.Model.entity.CreditApplication;
import com.example.todebjavaspringcreditapplicationproject.Service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/creditApplication")
public class CreditApplicationController {
    private final CreditApplicationService creditApplicationService;


    @PostMapping(value = "/applyForCredit")
    public String createCreditApplication(@Valid @RequestBody CreditApplicationDTO creditApplicationDTO) {
        creditApplicationService.addCreditApplication(creditApplicationDTO);
        log.info("Application created.");
        return creditApplicationService.getResult(creditApplicationDTO.getNationalId());
    }

    @GetMapping("/by-customer/{nationalId}")
    public List<CreditApplication> getAllApplicationByCustomer(@PathVariable String nationalId) {
        log.info("Application called by identity number.");
        return creditApplicationService.getAllApplicationByCustomer(nationalId);
    }
}
