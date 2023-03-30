package com.example.secretsanta.service;

import com.example.secretsanta.misc.exception.GroupNotFoundException;
import com.example.secretsanta.model.db.Group;
import com.example.secretsanta.model.db.Participant;
import com.example.secretsanta.model.dto.GroupDTO;
import com.example.secretsanta.repository.GroupRepository;
import com.example.secretsanta.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<GroupDTO> getAllGroups() {
        List<Group> groupList = groupRepository.findAll();
        List<GroupDTO> groupDTOList = new ArrayList<>();
        groupList.forEach( group -> groupDTOList.add(group.toDTO()));
        return groupDTOList;
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Override
    public void updateGroupById(Long id, GroupDTO groupDTO) {
        Group existingGroup = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        existingGroup.updateFromDTO(groupDTO);
        groupRepository.save(existingGroup);
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
        Group existingGroup = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        participantRepository.save(participant);
        List<Participant> participantListInGroup = existingGroup.getParticipants();
        participantListInGroup.add(participant);

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
