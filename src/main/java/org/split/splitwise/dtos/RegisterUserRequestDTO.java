package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.split.splitwise.services.UserService;

import java.io.Serializable;

@Setter
@Getter
public class RegisterUserRequestDTO implements Serializable {

    private String name;

    private String email;

    private String phoneNumber;

    private String password;
}
