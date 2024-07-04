package com.policymanagement.repository;

import com.policymanagement.entity.Policies;
import com.policymanagement.entity.PolicyTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoliciesRepository extends JpaRepository<Policies, Long> {
    List<Policies> findByPolicyTypesPolicyTypeId(Long policyTypeId);
    Optional<Policies> findByPolicyName(String policyName);

}
