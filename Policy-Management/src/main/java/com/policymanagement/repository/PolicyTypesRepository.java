package com.policymanagement.repository;

import com.policymanagement.entity.PolicyTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyTypesRepository extends JpaRepository <PolicyTypes, Long> {

}
