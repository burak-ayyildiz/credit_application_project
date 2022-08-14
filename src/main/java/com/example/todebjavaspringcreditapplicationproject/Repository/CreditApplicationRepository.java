package com.example.todebjavaspringcreditapplicationproject.Repository;

import com.example.todebjavaspringcreditapplicationproject.Model.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Integer> {

}
