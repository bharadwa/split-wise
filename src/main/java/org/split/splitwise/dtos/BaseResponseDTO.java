package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponseDTO {
    private ResponseStatus responseStatus;
    private String responseMessage;

}
