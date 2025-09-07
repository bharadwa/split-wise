package org.split.splitwise.command;

import org.split.splitwise.command.users.RegisterUserCommand;
import org.split.splitwise.command.users.UpdateProfileCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {

    private List<ICommand> commands;

    @Autowired
    public CommandRegistry(RegisterUserCommand registerUserCommand,
                           UpdateProfileCommand updateProfileCommand) {
        commands = new ArrayList<ICommand>();
        commands.add(registerUserCommand);
        commands.add(updateProfileCommand);
    }

    public void addCommand(ICommand command) {
        commands.add(command);
    }
    public void removeCommand(ICommand command) {
        commands.remove(command);
    }

    public void executeCommand(String input) {
        for (ICommand command : commands) {
            if (command.matches(input)) {
                command.execute(input);
                System.out.println("Executed command: " + input);
                return;
            }
        }
        System.out.println("Command not found: " + input);
    }
}
