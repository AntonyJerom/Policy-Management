package com.policymanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddPolicyTypeDto {
    @NotNull
    @Schema(description = "Type name of the policy", example = "Life Insurance")
    String typeName;

    @Schema(description = "Description of the policy type", example = "Covers health-related expenses")
    String description;
}
