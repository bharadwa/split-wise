package org.split.splitwise.command.expenses;

import org.split.splitwise.command.ICommand;
import org.split.splitwise.controllers.ExpenseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AddExpenseCommand implements ICommand {

    private final ExpenseController expenseController;

    @Autowired
    public AddExpenseCommand(ExpenseController expenseController){
        this.expenseController = expenseController;
    }

    @Override
    public boolean matches(String command) {
        return false;
    }

    @Override
    public void execute(String command) {

    }
}
