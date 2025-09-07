package org.split.splitwise.command.users;


import org.split.splitwise.command.CommandKeywords;
import org.split.splitwise.command.ICommand;
import org.split.splitwise.controllers.UserController;
import org.split.splitwise.dtos.ResponseStatus;
import org.split.splitwise.dtos.UpdateProfileRequestDTO;
import org.split.splitwise.dtos.UpdateProfileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UpdateProfileCommand implements ICommand {

    private final UserController userController;

    @Autowired
    public UpdateProfileCommand(UserController userController){
        this.userController=userController;
    }
    @Override
    public boolean matches(String command) {
        List<String> words = Arrays.asList(command.split(","));
        boolean matches =words.get(1).equalsIgnoreCase(CommandKeywords.UPDATE_PROFILE)&& words.size()==3;
        if(!matches){
            System.out.println("Invalid Command ,Usage:<User_id> UpdateProfile <password>");
            System.out.println("Example for Usage :1 UpdateProfile <Mclaren@2020>");
        }
        return matches;
    }

    @Override
    public void execute(String command) {
        List<String> words = Arrays.asList(command.split(","));
        if(words.size()!=3){
            return;
        }
        UpdateProfileRequestDTO requestDTO = new UpdateProfileRequestDTO();
        requestDTO.setPassword(words.get(2));
        requestDTO.setUserId(Long.parseLong(words.get(0)));
        UpdateProfileResponseDTO response =userController.updateProfile(requestDTO);

        if(response!=null){
            if(response.getResponseStatus()== ResponseStatus.SUCCESS) {
                System.out.println("Successfully updated Profile for user :"+response.getUserId());
            }else {
                System.out.println("failed to update Profile ! :"+response.getResponseMessage());
            }
        }

    }
}
