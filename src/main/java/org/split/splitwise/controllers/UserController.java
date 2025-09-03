package org.split.splitwise.controllers;

import org.split.splitwise.dtos.RegisterUserRequestDTO;
import org.split.splitwise.dtos.RegisterUserResponseDTO;
import org.split.splitwise.dtos.ResponseStatus;
import org.split.splitwise.exceptions.UserAlreadyExistsException;
import org.split.splitwise.models.User;
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
      RegisterUserResponseDTO response =new RegisterUserResponseDTO();
        try {
          User user=  this.userService.registerUser(registerUserRequestDTO.getName(),registerUserRequestDTO.getEmail(),registerUserRequestDTO.getPhoneNumber(),registerUserRequestDTO.getPassword());
          response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserAlreadyExistsException e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
