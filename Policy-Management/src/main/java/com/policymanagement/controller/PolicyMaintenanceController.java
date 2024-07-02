package com.policymanagement.controller;


import com.policymanagement.dto.RequestPolicyMaintenanceDto;
import com.policymanagement.dto.ResponsePolicyMaintenanceDto;
import com.policymanagement.service.PolicyMaintenanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policy-maintenance")
public class PolicyMaintenanceController {

    @Autowired
    private PolicyMaintenanceService policyMaintenanceService;

    @PostMapping
    public ResponseEntity<ResponsePolicyMaintenanceDto> savePolicyMaintenance(@Valid @RequestBody RequestPolicyMaintenanceDto requestDto) {
        ResponsePolicyMaintenanceDto responseDTO = policyMaintenanceService.savePolicyMaintenance(requestDto);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponsePolicyMaintenanceDto>> getAllPolicyMaintenances() {
        List<ResponsePolicyMaintenanceDto> responseDTOs = policyMaintenanceService.getAllPolicyMaintenances();
        return ResponseEntity.ok(responseDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicyMaintenance(@PathVariable Long id) {
        policyMaintenanceService.deletePolicyMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
