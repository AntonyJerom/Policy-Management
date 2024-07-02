package com.policymanagement.service;



import com.policymanagement.dto.RequestPolicyMaintenanceDto;
import com.policymanagement.dto.ResponsePolicyMaintenanceDto;
import jakarta.validation.Valid;


import java.util.List;

public interface PolicyMaintenanceService {

    ResponsePolicyMaintenanceDto savePolicyMaintenance(@Valid RequestPolicyMaintenanceDto requestDto);

    List<ResponsePolicyMaintenanceDto> getAllPolicyMaintenances();

    void deletePolicyMaintenance(Long id);
}
