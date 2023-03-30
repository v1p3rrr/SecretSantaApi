package com.example.secretsanta.service;

import com.example.secretsanta.model.db.Group;
import com.example.secretsanta.model.db.Participant;
import com.example.secretsanta.model.dto.GroupDTO;
import com.example.secretsanta.repository.GroupRepository;
import com.example.secretsanta.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;


    @Override
    public Long addGroup(Group group) {
        return groupRepository.saveAndFlush(group).getId();
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(); //todo
    }

    @Override
    public void updateGroupById(Long id, GroupDTO groupDTO) {

    }

    @Override
    public void deleteGroupById(Long id) {
        if (!groupRepository.existsById(id)){
            throw new RuntimeException(); //todo
        }
        groupRepository.deleteById(id);
    }

    @Override
    public Long addParticipantToGroupById(Long id, Participant participant) {
        return null;
    }

    @Override
    public void deleteParticipantFromGroupById(Long groupId, Long participantId) {

    }

    @Override
    public List<Participant> toss(Long id) {
        return null;
    }

    @Override
    public Participant getRecipientFromParticipantFromGroupById(Long groupId, Long participantId) {
        return null;
    }
}
