package com.policymanagement.configuration;

import com.policymanagement.dto.ResponseDataAgentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "agent-manage", url = "http://localhost:9093")
public interface AgentFeign {

    @GetMapping("/agent/getAgentById/{agentId}")
    ResponseDataAgentDto getAgentById(@PathVariable long agentId);

}
