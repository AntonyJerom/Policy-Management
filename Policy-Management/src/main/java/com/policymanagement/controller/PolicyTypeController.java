package com.policymanagement.controller;

import com.policymanagement.dto.RequestAddPolicyTypeDto;
import com.policymanagement.dto.ResponsePolicyTypeDto;
import com.policymanagement.service.PolicyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policyType")
public class PolicyTypeController {

    @Autowired
    private PolicyTypeService policyTypeService;

    @PostMapping("/addPolicyType")
    public ResponseEntity<ResponsePolicyTypeDto> addPolicyType(@RequestBody RequestAddPolicyTypeDto requestAddPolicyTypeDto) {
        ResponsePolicyTypeDto response = policyTypeService.addPolicyType(requestAddPolicyTypeDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
//        {        "policyTypeId": 1,
//                "typeName": "Life Insurance",
//                "policyDescription": "This policy has only life insurance"
//        }
    }

    @GetMapping("/getPolicyType")
    public ResponseEntity<List<ResponsePolicyTypeDto>> getAllPolicyType() {
        List<ResponsePolicyTypeDto> allPolicyType = policyTypeService.getAllPolicyType();
        return ResponseEntity.ok(allPolicyType);
    }

}