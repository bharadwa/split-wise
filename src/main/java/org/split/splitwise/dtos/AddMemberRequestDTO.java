package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddMemberRequestDTO {

    private long groupId;

    private long userId;

    private long memberId;
}
