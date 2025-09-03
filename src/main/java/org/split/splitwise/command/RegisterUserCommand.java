package org.split.splitwise.command;

import org.split.splitwise.controllers.UserController;
import org.split.splitwise.dtos.RegisterUserRequestDTO;
import org.split.splitwise.dtos.RegisterUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommand  implements ICommand{

    private final UserController userController;
    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }
    @Override
    public boolean matches(String command) {
        String words[] = command.split(" ");
        return words[0].equals("register");
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

    }
}
