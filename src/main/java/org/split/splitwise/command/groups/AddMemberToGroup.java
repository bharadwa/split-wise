package org.split.splitwise.command.groups;

import org.split.splitwise.command.CommandKeywords;
import org.split.splitwise.command.ICommand;
import org.split.splitwise.command.utils.CommandUtils;
import org.split.splitwise.controllers.GroupController;
import org.split.splitwise.dtos.AddMemberRequestDTO;
import org.split.splitwise.dtos.AddMemberResponseDTO;
import org.split.splitwise.dtos.ResponseStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AddMemberToGroup implements ICommand {

    private final GroupController groupController;

    public AddMemberToGroup(GroupController  groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String command) {
        List<String> words = CommandUtils.splitWords(command);
        boolean matches=words.get(1).equals(CommandKeywords.ADD_MEMBERS_TO_GROUP)&&words.size()==4;
        if(!matches){
            System.out.println("Invalid Command :Usage:<u1> AddMember <g1> <u2>");
            System.out.println("Example for Usage : <user_id>AddMember  <group_id> <member_id>");
        }
        return matches;
    }

    @Override
    public void execute(String command) {
        List<String> words = Arrays.asList(command.split(" "));
        if(words.size()!=4){
         System.out.println("words count didnt match");
         return;
        }
        AddMemberRequestDTO addMemberRequestDTO = new AddMemberRequestDTO();
        addMemberRequestDTO.setUserId(Long.parseLong(words.get(0)));
        addMemberRequestDTO.setGroupId(Long.parseLong(words.get(2)));
        addMemberRequestDTO.setMemberId(Long.parseLong(words.get(3)));
        AddMemberResponseDTO responseDTO=this.groupController.addMemberToGroup(addMemberRequestDTO);
        if(responseDTO!=null){
            if(responseDTO.getResponseStatus()== ResponseStatus.SUCCESS) {
                System.out.println("Successfully add member to group  :");
            }else {
                System.out.println("failed to add member to group "+responseDTO.getResponseMessage());
            }
        }
    }
}
