package com.hydatis.KycmicroserviceCQRS.command.controller;

import com.hydatis.KycmicroserviceCQRS.command.model.AgentPersonnePhysique;
import com.hydatis.KycmicroserviceCQRS.command.model.CategorieSocioProfesionnelle;
import com.hydatis.KycmicroserviceCQRS.command.model.Compte;
import com.hydatis.KycmicroserviceCQRS.command.model.Document;
import com.hydatis.KycmicroserviceCQRS.command.service.implementation.AgentPpCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pp")
public class AgentPpCommandController {

    private final AgentPpCommandService agentPpCommandService;

    @Autowired
    public AgentPpCommandController(AgentPpCommandService agentPpCommandService) {
        this.agentPpCommandService = agentPpCommandService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<AgentPersonnePhysique>> getAllAgents() {
        List<AgentPersonnePhysique> agents = agentPpCommandService.findAll();
        return ResponseEntity.ok(agents);
    }



    @PutMapping("/update")
    public ResponseEntity<AgentPersonnePhysique> updateAgent(@RequestBody AgentPersonnePhysique agentPersonnePhysique) {
        AgentPersonnePhysique updatedAgent = agentPpCommandService.update(agentPersonnePhysique);
        if (updatedAgent != null) {
            return ResponseEntity.ok(updatedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AgentPersonnePhysique> deleteAgent(@PathVariable("id") Long id) {
        AgentPersonnePhysique deletedAgent = agentPpCommandService.delete(id);
        if (deletedAgent != null) {
            return ResponseEntity.ok(deletedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<AgentPersonnePhysique> saveAgent(@RequestBody AgentPersonnePhysique agentPersonnePhysique) {


        AgentPersonnePhysique savedAgent = agentPpCommandService.save(agentPersonnePhysique);
        if (savedAgent != null) {
            return ResponseEntity.ok(savedAgent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
