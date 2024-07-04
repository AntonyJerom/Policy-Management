package com.policymanagement.serviceImpl;

import com.policymanagement.dto.RequestAddPolicyDto;
import com.policymanagement.dto.ResponsePolicyDto;
import com.policymanagement.dto.ResponseAddPolicyDto;
import com.policymanagement.entity.Policies;
import com.policymanagement.repository.PoliciesRepository;
import com.policymanagement.service.PolicyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PoliciesRepository policiesRepo;

    @Autowired
    private ModelMapper modelMapper;

    private ResponsePolicyDto convertToDto(Policies policy) {
        return modelMapper.map(policy, ResponsePolicyDto.class);
    }

    @Override
    public ResponseAddPolicyDto saveNewPolicy(RequestAddPolicyDto addPolicyDto) {
        Optional<Policies> byPolicyName = policiesRepo.findByPolicyName(addPolicyDto.getPolicyName());
        if (byPolicyName.isEmpty()) {
            Policies policies = modelMapper.map(addPolicyDto, Policies.class);

            Date currentDate = new Date();
            policies.setCreatedAt(currentDate);
            policies.setUpdatedAt(currentDate);
            policies.setPaymentMode("Online");
            policies.setStartDate(currentDate);

            Policies addedPolicy = policiesRepo.save(policies);

            ResponseAddPolicyDto response = modelMapper.map(addedPolicy, ResponseAddPolicyDto.class);
            response.setMessage("Policy saved successfully");
            return response;
        } else {
            ResponseAddPolicyDto response = modelMapper.map(byPolicyName.get(), ResponseAddPolicyDto.class);
            response.setMessage("Policy already exists");
            return response;
        }
    }
    @Override
    public List<ResponsePolicyDto> getAllPolicies() {
        List<Policies> listOfPolicy = policiesRepo.findAll();
        return listOfPolicy.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponsePolicyDto> getPoliciesByPolicyTypeId(Long policyTypeId) {
        List<Policies> policies = policiesRepo.findByPolicyTypesPolicyTypeId(policyTypeId);
        return policies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<ResponsePolicyDto> getAllPolicies() {
//
//        List<Policies> listOfPolicy = policiesRepo.findAll();
//
//        List<ResponsePolicyDto> responseList = new ArrayList<>();
//
//        for (Policies policy : listOfPolicy) {
//            ResponsePolicyDto response = getResponseListOfPolicy(policy);
//            responseList.add(response);
//        }
//
//        return responseList;
//    }
//
//    private static ResponsePolicyDto getResponseListOfPolicy(Policies policy) {
//        ResponsePolicyDto response = new ResponsePolicyDto();
//        response.setPolicyNumber(policy.getPolicyNumber());
//        response.setPolicyName(policy.getPolicyName());
//        response.setPremiumAmount(policy.getPremiumAmount());
//        response.setPaymentMode(policy.getPaymentMode());
//        response.setMaturityDate(policy.getMaturityDate());
//        response.setMaturityAmount(policy.getMaturityAmount());
//        response.setStatus(policy.getStatus());
//        response.setCreatedAt(policy.getCreatedAt());
//        return response;
//    }
//
//    @Override
//    public List<ResponsePolicyDto> getPoliciesByPolicyTypeId(Long policyTypeId) {
//        List<Policies> policies = policiesRepo.findByPolicyTypesPolicyTypeId(policyTypeId);
//        List<ResponsePolicyDto> responseList = new ArrayList<>();
//        for (Policies policy : policies) {
//            ResponsePolicyDto response = new ResponsePolicyDto();
//            response.setPolicyNumber(policy.getPolicyNumber());
//            response.setPolicyName(policy.getPolicyName());
//            response.setPremiumAmount(policy.getPremiumAmount());
//            response.setPaymentMode(policy.getPaymentMode());
//            response.setMaturityDate(policy.getMaturityDate());
//            response.setMaturityAmount(policy.getMaturityAmount());
//            response.setStatus(policy.getStatus());
//            response.setCreatedAt(policy.getCreatedAt());
//            responseList.add(response);
//        }
//
//        return responseList;
//    }

}
