package com.policymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePolicyMaintenanceDto {

    long maintenanceId;
    long policyId;
    String action;
    String details;
    long performedBy; // agent id FK
    Date performedAt;

}
