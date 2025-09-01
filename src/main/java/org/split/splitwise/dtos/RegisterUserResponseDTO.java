package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@Setter
@Getter
public class RegisterUserResponseDTO implements Serializable {
    private ResponseStatus responseStatus;
    private String email;
    private String name;
}
