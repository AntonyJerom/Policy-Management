package com.policymanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddPolicyDto {

    @Schema(description = "Policy number", example = "POL12345")
    @NotNull

    String policyNumber;

    @Schema(description = "Policy name", example = "Life Insurance")
    @NotNull
    @Size(min = 5, message = "policy Name must be more than 5 characters")
    String policyName;

    @Schema(description = "Premium amount", example = "5000")
    @NotNull
    int premiumAmount;

    @Schema(description = "Payment mode", example = "Online")
    String paymentMode;

    @Schema(description = "Maturity date", example = "1 Year")
    @NotNull
    String maturityDate;

    @Schema(description = "Maturity amount", example = "100000")
    @NotNull
    int maturityAmount;

    @Schema(description = "Status", example = "Active")
    String status;

    @Schema(description = "Policy type ID", example = "1")
    Long policyTypeId;

}
