package org.split.splitwise.command;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {

    private List<ICommand> commands;
    public CommandRegistry(RegisterUserCommand command) {
        commands = new ArrayList<ICommand>();
        commands.add(command);
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
