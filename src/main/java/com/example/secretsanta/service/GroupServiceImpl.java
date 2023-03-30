package com.example.secretsanta.service;

import com.example.secretsanta.misc.exception.GroupNotFoundException;
import com.example.secretsanta.misc.exception.ParticipantNotFoundException;
import com.example.secretsanta.model.db.Group;
import com.example.secretsanta.model.db.Participant;
import com.example.secretsanta.model.dto.GroupDTO;
import com.example.secretsanta.model.dto.ParticipantDTO;
import com.example.secretsanta.repository.GroupRepository;
import com.example.secretsanta.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
            throw new GroupNotFoundException(id);
        }
        groupRepository.deleteById(id);
    }

    @Override
    public Long addParticipantToGroupById(Long id, Participant participant) {
        Group existingGroup = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        Long participantId = participantRepository.saveAndFlush(participant).getId();
        List<Participant> participantListInGroup = existingGroup.getParticipants();
        participantListInGroup.add(participant);
        existingGroup.setParticipants(participantListInGroup);
        groupRepository.save(existingGroup);
        return participantId;
    }

    @Override
    public void deleteParticipantFromGroupById(Long groupId, Long participantId) {
        Group existingGroup = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        Participant existingParticipant = participantRepository.findById(participantId).orElseThrow(() -> new ParticipantNotFoundException(participantId));
        List<Participant> participantListInGroup = existingGroup.getParticipants();
        participantListInGroup.remove(existingParticipant);
        participantRepository.deleteById(participantId);
        existingGroup.setParticipants(participantListInGroup);
        groupRepository.save(existingGroup);
    }

//    {
//        "id": 1,
//            "name": "     ",
//            "description": "",
//            "participants": [
//        {
//            "id": 8,
//                "name": "dfsdfd",
//                "wish": "abababa"
//        }
//    ]
//    }

    @Override
    public List<ParticipantDTO> toss(Long id) {
        Group existingGroup = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        List<Participant> participantListInGroup = existingGroup.getParticipants();
        Collections.shuffle(participantListInGroup);

        for (int i = 0; i < participantListInGroup.size(); i++){
            if (i == participantListInGroup.size()-1){
                participantListInGroup.get(i).setRecipient(participantListInGroup.get(0));
            } else {
                participantListInGroup.get(i).setRecipient(participantListInGroup.get(i+1));
            }
        }
        participantListInGroup.forEach( participant -> System.out.println(participant.getId() + ", " + participant.getRecipient()));
        List<ParticipantDTO> participantDTOList = new ArrayList<>();
        participantListInGroup.forEach( participant -> participantDTOList.add(participant.toDTO()));
        return participantDTOList;
//        participantRepository.saveAll(participantListInGroup);
//        existingGroup.setParticipants(participantListInGroup);
//        groupRepository.save(existingGroup);
    }

    @Override
    public Participant getRecipientFromParticipantFromGroupById(Long groupId, Long participantId) {
        Group existingGroup = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        Participant existingParticipant = participantRepository.findById(participantId).orElseThrow(() -> new ParticipantNotFoundException(participantId));
        if (!existingGroup.getParticipants().contains(existingParticipant)){
            throw new ParticipantNotFoundException(participantId);
        }
        return existingParticipant.getRecipient();
    }
}
