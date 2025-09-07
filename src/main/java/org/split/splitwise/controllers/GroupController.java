package org.split.splitwise.controllers;

import org.split.splitwise.dtos.*;
import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.exceptions.UserNotFoundException;
import org.split.splitwise.models.Group;
import org.split.splitwise.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {


    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public AddGroupResponseDTO addGroup(AddGroupRequestDTO addGroupRequestDTO){
        AddGroupResponseDTO response =new AddGroupResponseDTO();

        try {
            Group group=this.groupService.addGroup(addGroupRequestDTO.getUserId(),addGroupRequestDTO.getName(),addGroupRequestDTO.getDescription());
            response.setGroupId(group.getId());
            response.setGroupName(group.getName());
            response.setCreatedUserId(group.getCreateBy().getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setResponseMessage("Successfully added group");
        } catch (UserNotFoundException e) {
            response.setResponseMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }

    public AddMemberResponseDTO addMemberToGroup(AddMemberRequestDTO addMemberRequestDTO){
        AddMemberResponseDTO response =new AddMemberResponseDTO();
        try{
            Group group=this.groupService.addMemberToGroup(addMemberRequestDTO.getGroupId(),addMemberRequestDTO.getUserId(),addMemberRequestDTO.getMemberId());
            response.setGroupId(group.getId());
            response.setGroupName(group.getName());
            response.setCreatedUserId(group.getCreateBy().getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setResponseMessage("Successfully added member to group");
        }catch (GroupNotFoundException | UserNotFoundException ge){
            response.setResponseMessage(ge.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;

    }



}
