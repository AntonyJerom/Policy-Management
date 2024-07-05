package com.policymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPolicyMaintenanceDto {

    long policyId;
    String action;
    String details;
    long performedBy; // agent id FK

}