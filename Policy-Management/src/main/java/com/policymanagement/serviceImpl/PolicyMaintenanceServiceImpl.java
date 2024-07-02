package com.policymanagement.serviceImpl;

import com.policymanagement.dto.RequestPolicyMaintenanceDto;
import com.policymanagement.dto.ResponsePolicyMaintenanceDto;
import com.policymanagement.entity.PolicyMaintenance;
import com.policymanagement.repository.PolicyMaintenanceRepository;
import com.policymanagement.service.PolicyMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyMaintenanceServiceImpl implements PolicyMaintenanceService {

    private final PolicyMaintenanceRepository policyMaintenanceRepository;

    @Autowired
    public PolicyMaintenanceServiceImpl(PolicyMaintenanceRepository policyMaintenanceRepository) {
        this.policyMaintenanceRepository = policyMaintenanceRepository;
    }

    @Override
    public ResponsePolicyMaintenanceDto savePolicyMaintenance(RequestPolicyMaintenanceDto requestDto) {
        PolicyMaintenance policyMaintenance = new PolicyMaintenance();
        policyMaintenance.setPolicyId(requestDto.getPolicyId());
        policyMaintenance.setAction(requestDto.getAction());
        policyMaintenance.setDetails(requestDto.getDetails());
        policyMaintenance.setPerformedBy(requestDto.getPerformedBy());
        policyMaintenance.setPerformedAt(requestDto.getPerformedAt());

        PolicyMaintenance savedPolicyMaintenance = policyMaintenanceRepository.save(policyMaintenance);

        return new ResponsePolicyMaintenanceDto(
                savedPolicyMaintenance.getMaintenanceId(),
                savedPolicyMaintenance.getPolicyId(),
                savedPolicyMaintenance.getAction(),
                savedPolicyMaintenance.getDetails(),
                savedPolicyMaintenance.getPerformedBy(),
                savedPolicyMaintenance.getPerformedAt()
        );
    }

    @Override
    public List<ResponsePolicyMaintenanceDto> getAllPolicyMaintenances() {
        List<PolicyMaintenance> policyMaintenances = policyMaintenanceRepository.findAll();
        return policyMaintenances.stream().map(policyMaintenance -> new ResponsePolicyMaintenanceDto(
                policyMaintenance.getMaintenanceId(),
                policyMaintenance.getPolicyId(),
                policyMaintenance.getAction(),
                policyMaintenance.getDetails(),
                policyMaintenance.getPerformedBy(),
                policyMaintenance.getPerformedAt()
        )).collect(Collectors.toList());
    }

    @Override
    public void deletePolicyMaintenance(Long id) {
        policyMaintenanceRepository.deleteById(id);
    }
}
