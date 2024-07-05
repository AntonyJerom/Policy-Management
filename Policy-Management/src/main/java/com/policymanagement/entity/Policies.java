package com.policymanagement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long policy_id;

    String policyNumber;
    String policyName;
    int premiumAmount;
    String paymentMode;
    Date startDate;
    String maturityDate;
    int maturityAmount;
    String Status;
    long agentId;   //Drop down
    Date createdAt;
    Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "policyTypeId")
    @JsonBackReference(value = "policyType")
    PolicyTypes policyTypes;

}
