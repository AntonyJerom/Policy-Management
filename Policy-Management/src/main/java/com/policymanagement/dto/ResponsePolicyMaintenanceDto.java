package com.policymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePolicyMaintenanceDto {

    Long maintenanceId;
    Long policyId;
    String action;
    String details;
    Long performedBy; // agent id FK
    LocalDateTime performedAt;

}
