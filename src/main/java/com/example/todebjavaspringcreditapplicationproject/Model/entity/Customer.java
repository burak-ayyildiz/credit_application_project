package com.example.todebjavaspringcreditapplicationproject.Model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "national_id")
    private String nationalId;

    @NotNull(message = "Firstname can not be null")
    private String firstname;

    @NotNull(message = "Lastname can not be null")
    private String lastname;

    @Column(name = "monthly_income")
    @NotNull(message = "Income can not be null")
    private int monthlyIncome;

    @NotNull(message = "Phone can not be null")
    @Size(min = 10, max = 10, message = "Phone number should be exact 10 characters.")
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE)
    private List<CreditApplication> creditApplications;

}