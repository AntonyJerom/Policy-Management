package com.policymanagement.service;

import com.policymanagement.dto.RequestAddPolicyDto;
import com.policymanagement.dto.ResponsePolicyDto;
import com.policymanagement.dto.ResponseAddPolicyDto;

import java.util.List;

public interface PolicyService {

    ResponseAddPolicyDto saveNewPolicy(RequestAddPolicyDto addPolicyDto);

    List<ResponsePolicyDto> getAllPolicies();

    List<ResponsePolicyDto> getPoliciesByPolicyTypeId(Long policyTypeId);

}
