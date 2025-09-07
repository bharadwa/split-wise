package org.split.splitwise.services;

import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.exceptions.UserNotFoundException;
import org.split.splitwise.models.Group;
import org.split.splitwise.models.User;
import org.split.splitwise.repositories.GroupRepository;
import org.split.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
                            UserRepository userRepository,
                            UserService userService) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public Group addGroup(long userId, String name, String description) throws UserNotFoundException {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Group group = new Group();
        group.setName(name);
        group.setDescription(description);
        group.setGroupMembers(new ArrayList<>());
        group.getGroupMembers().add(user);
        group.setCreateBy(user);
        return this.groupRepository.save(group);
    }

    @Override
    public Group addMemberToGroup(long groupId,long userId, long memberId) throws UserNotFoundException,GroupNotFoundException {
        Group group = this.groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException("Group not found"));
        User user= this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id :" +userId + " not found"));
        User member= this.userService.checkMemberExistsInGroup(memberId);
        group.getGroupMembers().add(member);
        return this.groupRepository.save(group);
    }
}
