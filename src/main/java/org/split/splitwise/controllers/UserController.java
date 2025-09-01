package org.split.splitwise.controllers;

import org.split.splitwise.dtos.RegisterUserRequestDTO;
import org.split.splitwise.dtos.RegisterUserResponseDTO;
import org.split.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService){
        this.userService  =userService;
    }

    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO){

    }
}
