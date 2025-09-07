package org.split.splitwise.services;

import org.split.splitwise.models.Group;
import org.split.splitwise.models.Groups;

public interface GroupService {

    Group addGroup(long userId, String name, String description);
}
