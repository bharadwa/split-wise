package org.split.splitwise.services;

import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.exceptions.UserNotFoundException;
import org.split.splitwise.models.Group;

public interface GroupService {

    Group addGroup(long userId, String name, String description) throws UserNotFoundException;

    Group addMemberToGroup(long groupId,long userId, long memberId) throws UserNotFoundException, GroupNotFoundException;
}
