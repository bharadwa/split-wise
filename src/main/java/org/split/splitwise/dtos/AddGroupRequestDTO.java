package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.split.splitwise.models.BaseModel;

@Setter
@Getter
public class AddGroupRequestDTO extends BaseResponseDTO {

    private long userId;

    private String name;

    private String description;
}
