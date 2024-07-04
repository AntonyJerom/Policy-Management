package com.policymanagement.entity;


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
    Long policy_id;

    String policyNumber;
    String policyName;
    int premiumAmount;
    String paymentMode;
    Date startDate;
    String maturityDate;
    int maturityAmount;
    String Status;
    Long agentId;
    Date createdAt;
    Date updatedAt;

    @ManyToOne
    PolicyTypes policyTypes;

}
