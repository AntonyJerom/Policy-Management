package com.policymanagement.controller;

import com.policymanagement.dto.RequestAddPolicyDto;
import com.policymanagement.dto.ResponsePolicyDto;
import com.policymanagement.dto.ResponseAddPolicyDto;
import com.policymanagement.service.PolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class PoliciesController {
    @Autowired
    private PolicyService policyService;

    @Operation(summary = "Save policy ", description = "Add new policy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added the policy"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/addPolicy")
    public ResponseEntity<ResponseAddPolicyDto> addNewPolicy (@RequestBody RequestAddPolicyDto addPolicyDto){
        ResponseAddPolicyDto responseAddPolicyDto = policyService.saveNewPolicy(addPolicyDto);
        return new ResponseEntity<>(responseAddPolicyDto,HttpStatus.CREATED);
//      {          "policyNumber":"py0074",
//                "policyName":"policy 4",
//                "premiumAmount":1000,
//                "paymentMode":"Online",
//                "maturityDate":"3years",
//                "maturityAmount":30000,
//                "createdAt":"2024-07-01T07:30:02.186+00:00",
//                "status":"active"}
    }

    @Operation(summary = "Get all policies ", description = "Retrieve a list of policies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of policies"),
            @ApiResponse(responseCode = "404", description = "Policy type not found")
    })
    @GetMapping("/getAllPolicies")
    public ResponseEntity<List<ResponsePolicyDto>> getAllPolicies(){
        List<ResponsePolicyDto> allPolicies = policyService.getAllPolicies();
        return ResponseEntity.ok(allPolicies);
    }
    @Operation(summary = "Get all policies by policy type ID", description = "Retrieve a list of policies based on policy type ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of policies"),
            @ApiResponse(responseCode = "404", description = "Policy type not found")
    })
    @GetMapping("/getPolicyByTypeId/{policyTypeId}")
    public ResponseEntity<List<ResponsePolicyDto>> getAllPoliciesByPolicyType(@PathVariable long policyTypeId){
        List<ResponsePolicyDto> policiesByPolicyTypeId = policyService.getPoliciesByPolicyTypeId(policyTypeId);
        return ResponseEntity.ok(policiesByPolicyTypeId);
    }


}
