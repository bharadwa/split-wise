package org.split.splitwise.command;

import org.split.splitwise.command.expenses.AddExpenseCommand;
import org.split.splitwise.command.groups.AddGroupCommand;
import org.split.splitwise.command.groups.AddMemberToGroupCommand;
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
    public CommandRegistry(RegisterUserCommand registerUserCommand, UpdateProfileCommand updateProfileCommand, AddGroupCommand addGroupCommand, AddMemberToGroupCommand addMemberToGroupCommand, AddExpenseCommand addExpenseCommand) {
        commands = new ArrayList<ICommand>();
        commands.add(registerUserCommand);
        commands.add(updateProfileCommand);
        commands.add(addGroupCommand);
        commands.add(addMemberToGroupCommand);
        commands.add(addExpenseCommand);
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
