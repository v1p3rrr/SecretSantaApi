package com.example.secretsanta.controller;

import com.example.secretsanta.model.db.Group;
import com.example.secretsanta.model.db.Participant;
import com.example.secretsanta.model.dto.GroupDTO;
import com.example.secretsanta.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
@Tag(name = "Secret santa API", description = "API for Secret Santa Game")
public class SantaController {

    private final GroupService groupService;

    @GetMapping("")
    @Operation(summary = "Get all groups")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getAllGroups(){
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    @Operation(description = "get group by its id")
    @ResponseStatus(HttpStatus.OK)
    public Group getGroupById(@Parameter(name = "group id", required = true) @PathVariable Long id){
        return groupService.getGroupById(id);
    }

    @PostMapping("")
    @Operation(description = "add new group")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addGroup(@RequestBody Group group){
        return groupService.addGroup(group);
    }

    @PutMapping("/{id}")
    @Operation(description = "update group")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGroupById(Long id, GroupDTO groupDTO){
        groupService.updateGroupById(id, groupDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "delete group by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroupById(@Parameter(name = "group id", required = true) @PathVariable Long id){
        groupService.deleteGroupById(id);
    }

    @PostMapping("/{id}/participant")
    @Operation(description = "add new participant to existing group")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addParticipantToGroupById(@Parameter(name = "group id", required = true) @PathVariable Long id, @RequestBody Participant participant){
        return groupService.addParticipantToGroupById(id, participant);
    }

    @DeleteMapping("/{groupId}/participant/{participantId}")
    @Operation(description = "delete participant from group by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParticipantFromGroupById(@Parameter(name = "group id", required = true) @PathVariable Long groupId, @Parameter(name = "participant id") @PathVariable Long participantId){
        groupService.deleteParticipantFromGroupById(groupId, participantId);
    }

    @PostMapping("/{id}/toss")
    @Operation(description = "get group by its id")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Participant> toss(@Parameter(name = "group id", required = true) @PathVariable Long id){
        return groupService.toss(id);
    }

    @GetMapping("/{groupId}/participant/{participantId}/recipient")
    @Operation(description = "get recipient from participant in group by their ids")
    @ResponseStatus(HttpStatus.CREATED)
    public Participant getRecipientFromParticipantFromGroupById(@Parameter(name = "group id", required = true) @PathVariable Long groupId, @Parameter(name = "participant id") @PathVariable Long participantId){
        return groupService.getRecipientFromParticipantFromGroupById(groupId, participantId);
    }
}
