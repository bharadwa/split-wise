package org.split.splitwise.command.users;

import org.split.splitwise.command.CommandKeywords;
import org.split.splitwise.command.ICommand;
import org.split.splitwise.command.utils.CommandUtils;
import org.split.splitwise.controllers.UserController;
import org.split.splitwise.dtos.RegisterUserRequestDTO;
import org.split.splitwise.dtos.RegisterUserResponseDTO;
import org.split.splitwise.dtos.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterUserCommand  implements ICommand {

    private final UserController userController;

    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String command) {
        List<String> words = CommandUtils.splitWords(command);
        boolean matches= words.get(0).equals(CommandKeywords.REGISTER)&&words.size()==5;
        if(!matches) {
            System.out.println("Invalid Command ,Usage:Register <name> <email> <phoneNumber> <password>");
            System.out.println("Example for Usage :Register <vinsmokesanji> <bharadwajreddy9@gmail.com> <003> <namisswwaann>");
        }
        return matches;
    }

    @Override
    public void execute(String command) {
        String words[] = command.split(" ");
        if(words.length!=5){
            System.out.println("Invalid command");
            return;
        }
        String name=words[1];
        String email=words[2];
        String phoneNumber=words[3];
        String password=words[4];
        RegisterUserRequestDTO userRequestDTO =new RegisterUserRequestDTO();
        userRequestDTO.setName(name);
        userRequestDTO.setEmail(email);
        userRequestDTO.setPhoneNumber(phoneNumber);
        userRequestDTO.setPassword(password);
        RegisterUserResponseDTO response=this.userController.registerUser(userRequestDTO);

        if(response!=null){
            if(response.getResponseStatus()== ResponseStatus.SUCCESS) {
                System.out.println("User with id :" + response.getUserId() + " successfully registered!");
            }else {
                System.out.println("failed to register user! :"+response.getResponseMessage());
            }
        }
    }
}
