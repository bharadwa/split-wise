package org.split.splitwise.services;

import org.split.splitwise.models.Group;
import org.split.splitwise.repositories.GroupRepository;
import org.split.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    private UserRepository userRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
                            UserRepository userRepository) {
              this.groupRepository = groupRepository;
              this.userRepository = userRepository;
    }

    @Override
    public Group addGroup(long userId, String name, String description) {
        return null;
    }
}
