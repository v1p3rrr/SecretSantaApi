package com.example.secretsanta.repository;

import com.example.secretsanta.model.db.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
