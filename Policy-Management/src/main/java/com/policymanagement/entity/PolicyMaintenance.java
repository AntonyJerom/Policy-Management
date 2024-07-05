package com.policymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maintenanceId;

    @NotNull(message = "Policy ID should not be null")
    @Min(value = 1, message = "Policy ID should be a positive number")
    private long policyId;

    @NotNull(message = "Action should not be null")
    @Size(max = 255, message = "Action should not exceed 255 characters")
    private String action;

    @Size(max = 1000, message = "Details should not exceed 1000 characters")
    private String details;

    @NotNull(message = "Performed By should not be null")
    @Min(value = 1, message = "Performed By should be a positive number")
    private long performedBy;

    @NotNull(message = "Performed At should not be null")
    private Date performedAt;
}
