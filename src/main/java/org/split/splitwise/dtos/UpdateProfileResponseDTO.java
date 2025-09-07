package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileResponseDTO extends BaseResponseDTO {
    private String email;
    private String name;
    private String userId;
}
