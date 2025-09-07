package org.split.splitwise.command.groups;

import org.split.splitwise.command.CommandKeywords;
import org.split.splitwise.command.ICommand;
import org.split.splitwise.command.utils.CommandUtils;
import org.split.splitwise.controllers.GroupController;
import org.split.splitwise.dtos.AddGroupRequestDTO;
import org.split.splitwise.dtos.AddGroupResponseDTO;
import org.split.splitwise.dtos.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddGroupCommand implements ICommand {


    private final GroupController groupController;

    @Autowired
    public AddGroupCommand(GroupController groupController) {
        this.groupController = groupController;
    }
    @Override
    public boolean matches(String command) {
        List<String> words = CommandUtils.splitWords(command);
        boolean matches=words.get(1).equals(CommandKeywords.ADD_GROUPS)&&words.size()==3;
        if(!matches){
            System.out.println("Invalid Command :Usage:<u1> AddGroup <GroupName>");
            System.out.println("Example for Usage : <user_id>AddGroup  <Roommates> ");
        }
        return matches;
    }


    @Override
    public void execute(String command) {

        List<String> words = CommandUtils.splitWords(command);
        if(words.size()!=4){
            return ;
        }
        AddGroupRequestDTO requestDTO = new AddGroupRequestDTO();
        requestDTO.setDescription("Add group by user");
        requestDTO.setUserId(Long.parseLong(words.get(0)));
        requestDTO.setName(words.get(2));
        AddGroupResponseDTO response =this.groupController.addGroup(requestDTO);

        if(response!=null){
            if(response.getResponseStatus()== ResponseStatus.SUCCESS) {
                System.out.println("Successfully add group  :"+response.getGroupId());
            }else {
                System.out.println("failed to add group "+response.getResponseMessage());
            }
        }
    }
}
