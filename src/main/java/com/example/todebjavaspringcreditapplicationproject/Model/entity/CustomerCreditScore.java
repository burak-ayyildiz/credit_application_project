package com.example.todebjavaspringcreditapplicationproject.Model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer_credit_score")
public class CustomerCreditScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_national_id",referencedColumnName = "national_id")
    private Customer customer;

    @NotNull(message = "Credit Score can not be null")
    @Column(name = "credit_score")
    private int creditScore;
}
