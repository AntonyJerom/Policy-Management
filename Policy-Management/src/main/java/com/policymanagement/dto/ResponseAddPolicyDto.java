package com.policymanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAddPolicyDto {

    @Schema(description = "Policy number", example = "POL12345")
    String policyNumber;

    @Schema(description = "Policy name", example = "Health Insurance")
    String policyName;

    @Schema(description = "Response message", example = "Policy saved successfully")
    String message;
}
