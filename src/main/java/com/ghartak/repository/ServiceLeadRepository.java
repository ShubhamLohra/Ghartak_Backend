package com.ghartak.repository;

import com.ghartak.model.ServiceLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceLeadRepository extends JpaRepository<ServiceLead, Long> {
    List<ServiceLead> findByLeadStatus(String leadStatus);
    List<ServiceLead> findByAssignedWorkerId(Long workerId);
}
