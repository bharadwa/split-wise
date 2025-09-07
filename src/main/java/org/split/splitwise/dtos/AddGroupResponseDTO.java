package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddGroupResponseDTO extends  BaseResponseDTO {

    private long groupId;
    private String groupName;
    private long createdUserId;

}
