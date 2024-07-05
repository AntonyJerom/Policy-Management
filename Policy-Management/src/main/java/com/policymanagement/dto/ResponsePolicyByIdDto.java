package com.policymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePolicyByIdDto {

    //Policy
    String policyNumber;
    String policyName;
    int premiumAmount;
    String paymentMode;
    Date startDate;
    String maturityDate;
    int maturityAmount;
    String Status;

    //Agent
    String agentName;
    private String agentContactInfo;
}
