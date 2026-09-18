package com.ghartak.controller;

import com.ghartak.model.ServiceLead;
import com.ghartak.model.User;
import com.ghartak.repository.ServiceLeadRepository;
import com.ghartak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin(origins = "*")
public class LeadController {

    @Autowired
    private ServiceLeadRepository leadRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<ServiceLead>> getAvailableLeads() {
        return ResponseEntity.ok(leadRepository.findByLeadStatus("AVAILABLE"));
    }

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<ServiceLead>> getWorkerLeads(@PathVariable Long workerId) {
        return ResponseEntity.ok(leadRepository.findByAssignedWorkerId(workerId));
    }

    @PostMapping("/{leadId}/accept")
    public ResponseEntity<?> acceptLead(@PathVariable Long leadId, @RequestParam("workerId") Long workerId) {
        Optional<ServiceLead> leadOpt = leadRepository.findById(leadId);
        Optional<User> workerOpt = userRepository.findById(workerId);

        if (leadOpt.isEmpty() || workerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Lead or Worker not found");
        }

        ServiceLead lead = leadOpt.get();
        User worker = workerOpt.get();

        if (!"AVAILABLE".equals(lead.getLeadStatus())) {
            return ResponseEntity.badRequest().body("Lead already accepted by another partner");
        }

        if (worker.getDailyLeadsRemaining() <= 0) {
            return ResponseEntity.badRequest().body("Daily lead limit reached (Max 3 leads/day)");
        }

        lead.setLeadStatus("ACCEPTED");
        lead.setAssignedWorker(worker);
        leadRepository.save(lead);

        worker.setDailyLeadsRemaining(worker.getDailyLeadsRemaining() - 1);
        worker.setTotalEarnings(worker.getTotalEarnings() + lead.getEstimatedPayout());
        worker.setCompletedJobs(worker.getCompletedJobs() + 1);
        userRepository.save(worker);

        return ResponseEntity.ok(lead);
    }
}
