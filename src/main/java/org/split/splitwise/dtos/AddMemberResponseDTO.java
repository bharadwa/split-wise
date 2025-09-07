package org.split.splitwise.dtos;


import lombok.Setter;

@Setter
public class AddMemberResponseDTO extends BaseResponseDTO {

    private long groupId;

    private long createdUserId;

    private String groupName;

}
