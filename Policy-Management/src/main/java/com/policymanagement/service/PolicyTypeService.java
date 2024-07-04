package com.policymanagement.service;

import com.policymanagement.dto.RequestAddPolicyTypeDto;
import com.policymanagement.dto.ResponsePolicyDto;
import com.policymanagement.dto.ResponsePolicyTypeDto;

import java.util.List;

public interface PolicyTypeService {

    ResponsePolicyTypeDto addPolicyType(RequestAddPolicyTypeDto requestAddPolicyTypeDto);

    List<ResponsePolicyTypeDto> getAllPolicyType();

}
