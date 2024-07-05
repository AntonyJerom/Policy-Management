package com.policymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePolicyTypeDto {

    long policyTypeId;
    String typeName;
    String policyDescription;
}
