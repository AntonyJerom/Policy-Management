package com.policymanagement.serviceImpl;

import com.policymanagement.dto.RequestAddPolicyTypeDto;
import com.policymanagement.dto.ResponsePolicyDto;
import com.policymanagement.dto.ResponsePolicyTypeDto;
import com.policymanagement.entity.Policies;
import com.policymanagement.entity.PolicyTypes;
import com.policymanagement.repository.PolicyTypesRepository;
import com.policymanagement.service.PolicyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyTypeServiceImpl implements PolicyTypeService {

    @Autowired
    private PolicyTypesRepository policyTypesRepo;

    @Override
    public ResponsePolicyTypeDto addPolicyType(RequestAddPolicyTypeDto requestAddPolicyTypeDto) {

        PolicyTypes policyTypes = new PolicyTypes();
        policyTypes.setTypeName(requestAddPolicyTypeDto.getTypeName());
        policyTypes.setDescription(requestAddPolicyTypeDto.getDescription());
        PolicyTypes savedPolicyType = policyTypesRepo.save(policyTypes);

        ResponsePolicyTypeDto response = new ResponsePolicyTypeDto();
        response.setPolicyTypeId(savedPolicyType.getPolicyTypeId());
        response.setTypeName(savedPolicyType.getTypeName());
        response.setPolicyDescription(savedPolicyType.getDescription());

        return response;
    }
    @Override
    public List<ResponsePolicyTypeDto> getAllPolicyType() {
        List<PolicyTypes> list = policyTypesRepo.findAll();
        List<ResponsePolicyTypeDto> response = new ArrayList<>();

        for (PolicyTypes policyType : list) {
            ResponsePolicyTypeDto dto = new ResponsePolicyTypeDto();
            dto.setPolicyTypeId(policyType.getPolicyTypeId());
            dto.setTypeName(policyType.getTypeName());
            dto.setPolicyDescription(policyType.getDescription());
            response.add(dto);
        }

        return response;
    }



}
