package com.policymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDataAgentDto {
    private String agentName;
    private String agentContactInfo;
    private String territoryRefName;
}
