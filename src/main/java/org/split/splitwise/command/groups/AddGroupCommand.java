package org.split.splitwise.command.groups;

import org.split.splitwise.command.ICommand;
import org.split.splitwise.services.GroupService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AddGroupCommand implements ICommand {

    @Override
    public boolean matches(String command) {
        return false;
    }

    @Override
    public void execute(String command) {

    }
}
