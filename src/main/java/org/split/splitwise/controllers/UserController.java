package org.split.splitwise.controllers;

import org.split.splitwise.dtos.*;
import org.split.splitwise.exceptions.UserAlreadyExistsException;
import org.split.splitwise.exceptions.UserNotFoundException;
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
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }

    public UpdateProfileResponseDTO updateProfile(UpdateProfileRequestDTO updateProfileRequestDTO){

        UpdateProfileResponseDTO response =new UpdateProfileResponseDTO();
        try {
            User user =this.userService.updateProfile(updateProfileRequestDTO.getUserId(),updateProfileRequestDTO.getPassword());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }
}
