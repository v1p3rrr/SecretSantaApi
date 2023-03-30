package com.example.secretsanta.service;

import com.example.secretsanta.model.db.Group;
import com.example.secretsanta.model.db.Participant;
import com.example.secretsanta.model.dto.GroupDTO;

import java.util.List;

public interface GroupService {
    Long addGroup(Group group);
    List<Group> getAllGroups();
    Group getGroupById(Long id);
    void updateGroupById(Long id, GroupDTO groupDTO);
    void deleteGroupById(Long id);
    Long addParticipantToGroupById(Long groupId, Participant participant);
    void deleteParticipantFromGroupById(Long groupId, Long participantId);
    List<Participant> toss(Long id);
    Participant getRecipientFromParticipantFromGroupById(Long groupId, Long participantId);
}
