package org.split.splitwise.command;

import lombok.AllArgsConstructor;
import org.split.splitwise.controllers.SettleUpController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettleUpCommand  implements ICommand{

    private final SettleUpController settleUpController;

    @Autowired
    public SettleUpCommand(SettleUpController settleUpController) {
        this.settleUpController=settleUpController;

    }
    @Override
    public boolean matches(String command) {
        return false;
    }

    @Override
    public void execute(String command) {

    }
}
