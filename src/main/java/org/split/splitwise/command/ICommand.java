package org.split.splitwise.command;

public interface ICommand {

    public boolean matches(String command);

    public void execute(String command);
}
