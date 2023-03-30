package com.example.secretsanta.service;

import com.example.secretsanta.model.db.Participant;

import java.util.List;

public interface ParticipantService {
    Long addParticipant();
    List<Participant> getAllParticipants();
    Participant getParticipantById();
    void updateParticipantById();
    void deleteParticipantById();
}
