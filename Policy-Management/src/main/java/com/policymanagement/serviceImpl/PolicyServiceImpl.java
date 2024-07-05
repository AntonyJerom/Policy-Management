package com.policymanagement.serviceImpl;

import com.policymanagement.configuration.AgentFeign;
import com.policymanagement.dto.*;
import com.policymanagement.entity.Policies;
import com.policymanagement.entity.PolicyTypes;
import com.policymanagement.repository.PoliciesRepository;
import com.policymanagement.repository.PolicyTypesRepository;
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
    private PolicyTypesRepository typeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AgentFeign agentFeign;

    private ResponsePolicyDto convertToDto(Policies policy) {
        return modelMapper.map(policy, ResponsePolicyDto.class);
    }

    @Override
    public ResponseAddPolicyDto saveNewPolicy(RequestAddPolicyDto addPolicyDto) {
        Optional<Policies> existingPolicy = policiesRepo.findByPolicyName(addPolicyDto.getPolicyName());

        if (existingPolicy.isPresent()) {
            return createResponse(existingPolicy.get(), "Policy already exists");
        }

        Policies policies = modelMapper.map(addPolicyDto, Policies.class);
        Date currentDate = new Date();
        policies.setCreatedAt(currentDate);
        policies.setUpdatedAt(currentDate);
        policies.setPaymentMode("Online");
        policies.setStartDate(currentDate);

        PolicyTypes policyType = typeRepo.findById(addPolicyDto.getPolicyTypeId())
                .orElseThrow(() -> new RuntimeException("PolicyType not found with id: " + addPolicyDto.getPolicyTypeId()));

        policies.setPolicyTypes(policyType);
        Policies addedPolicy = policiesRepo.save(policies);

        return createResponse(addedPolicy, "Policy saved successfully");
    }

    private ResponseAddPolicyDto createResponse(Policies policy, String message) {
        ResponseAddPolicyDto response = modelMapper.map(policy, ResponseAddPolicyDto.class);
        response.setMessage(message);
        return response;
    }

    @Override
    public List<ResponsePolicyDto> getAllPolicies() {
        List<Policies> listOfPolicy = policiesRepo.findAll();
        return listOfPolicy.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponsePolicyDto> getPoliciesByPolicyTypeId(long policyTypeId) {
        List<Policies> policies = policiesRepo.findByPolicyTypesPolicyTypeId(policyTypeId);
        return policies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponsePolicyByIdDto getPolicyById(long policyId) {
        Optional<Policies> byId = policiesRepo.findById(policyId);
        ResponsePolicyByIdDto response = new ResponsePolicyByIdDto();
        if(byId.isPresent()) {
            Policies policy = byId.get();
            ResponseDataAgentDto agentById = agentFeign.getAgentById(policy.getAgentId());

            response.setAgentName(agentById.getAgentName());
            response.setAgentContactInfo(agentById.getAgentContactInfo());

            response.setPolicyNumber(policy.getPolicyNumber());
            response.setStartDate(policy.getStartDate());
            response.setMaturityAmount(policy.getMaturityAmount());
            response.setPolicyName(policy.getPolicyName());
            response.setMaturityDate(policy.getMaturityDate());
            response.setPaymentMode(policy.getPaymentMode());
            response.setStatus(policy.getStatus());
            response.setPremiumAmount(policy.getPremiumAmount());
        }
        else {throw new RuntimeException();
        }
        return response;
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
