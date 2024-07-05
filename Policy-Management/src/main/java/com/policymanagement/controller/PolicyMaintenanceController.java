package com.policymanagement.controller;

import com.policymanagement.dto.RequestPolicyMaintenanceDto;
import com.policymanagement.dto.ResponsePolicyMaintenanceDto;
import com.policymanagement.service.PolicyMaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policy-maintenance")
public class PolicyMaintenanceController {

    @Autowired
    private PolicyMaintenanceService policyMaintenanceService;

    @Operation(summary = "Save policy maintenance", description = "Add new policy maintenance record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added the policy maintenance record"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<ResponsePolicyMaintenanceDto> savePolicyMaintenance(@Valid @RequestBody RequestPolicyMaintenanceDto requestDto) {
        ResponsePolicyMaintenanceDto responseDTO = policyMaintenanceService.savePolicyMaintenance(requestDto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all policy maintenance records", description = "Retrieve a list of all policy maintenance records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of policy maintenance records"),
            @ApiResponse(responseCode = "404", description = "No policy maintenance records found")
    })
    @GetMapping
    public ResponseEntity<List<ResponsePolicyMaintenanceDto>> getAllPolicyMaintenances() {
        List<ResponsePolicyMaintenanceDto> responseDTOs = policyMaintenanceService.getAllPolicyMaintenances();
        return ResponseEntity.ok(responseDTOs);
    }
    @Operation(summary = "Update policy maintenance record", description = "Update an existing policy maintenance record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the policy maintenance record"),
            @ApiResponse(responseCode = "404", description = "Policy maintenance record not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponsePolicyMaintenanceDto> updatePolicyMaintenance(@PathVariable Long id, @Valid @RequestBody RequestPolicyMaintenanceDto requestDto) {
        ResponsePolicyMaintenanceDto responseDTO = policyMaintenanceService.updatePolicyMaintenance(id, requestDto);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Delete policy maintenance record", description = "Delete a policy maintenance record by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the policy maintenance record"),
            @ApiResponse(responseCode = "404", description = "Policy maintenance record not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicyMaintenance(@PathVariable Long id) {
        policyMaintenanceService.deletePolicyMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
