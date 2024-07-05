package com.policymanagement.serviceImpl;

import com.policymanagement.dto.RequestPolicyMaintenanceDto;
import com.policymanagement.dto.ResponsePolicyMaintenanceDto;
import com.policymanagement.entity.PolicyMaintenance;
import com.policymanagement.repository.PolicyMaintenanceRepository;
import com.policymanagement.service.PolicyMaintenanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PolicyMaintenanceServiceImpl implements PolicyMaintenanceService {

    private final PolicyMaintenanceRepository policyMaintenanceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PolicyMaintenanceServiceImpl(PolicyMaintenanceRepository policyMaintenanceRepository, ModelMapper modelMapper) {
        this.policyMaintenanceRepository = policyMaintenanceRepository;
        this.modelMapper = modelMapper;
    }

    private ResponsePolicyMaintenanceDto convertToDto(PolicyMaintenance policyMaintenance) {
        return modelMapper.map(policyMaintenance, ResponsePolicyMaintenanceDto.class);
    }

    @Override
    public ResponsePolicyMaintenanceDto savePolicyMaintenance(RequestPolicyMaintenanceDto requestDto) {
        PolicyMaintenance policyMaintenance = modelMapper.map(requestDto, PolicyMaintenance.class);
        PolicyMaintenance savedPolicyMaintenance = policyMaintenanceRepository.save(policyMaintenance);
        return convertToDto(savedPolicyMaintenance);
    }

    @Override
    public List<ResponsePolicyMaintenanceDto> getAllPolicyMaintenances() {
        List<PolicyMaintenance> policyMaintenances = policyMaintenanceRepository.findAll();
        return policyMaintenances.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public ResponsePolicyMaintenanceDto updatePolicyMaintenance(long id, RequestPolicyMaintenanceDto requestDto) {
        Optional<PolicyMaintenance> existingPolicyMaintenanceOpt = policyMaintenanceRepository.findById(id);
        if (existingPolicyMaintenanceOpt.isPresent()) {
            PolicyMaintenance existingPolicyMaintenance = existingPolicyMaintenanceOpt.get();
            modelMapper.map(requestDto, existingPolicyMaintenance);
            PolicyMaintenance updatedPolicyMaintenance = policyMaintenanceRepository.save(existingPolicyMaintenance);
            return convertToDto(updatedPolicyMaintenance);
        } else {
            throw new RuntimeException("Policy Maintenance not found with id: " + id);
        }
    }

    @Override
    public void deletePolicyMaintenance(long id) {
        policyMaintenanceRepository.deleteById(id);
    }
}
