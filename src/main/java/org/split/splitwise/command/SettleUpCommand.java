package org.split.splitwise.command;

import lombok.AllArgsConstructor;
import org.split.splitwise.controllers.SettleUpController;
import org.split.splitwise.dtos.ResponseStatus;
import org.split.splitwise.dtos.SettleUpGroupRequestDTO;
import org.split.splitwise.dtos.SettleUpGroupResponseDTO;
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
        String words[] = command.split(" ");
        return words[0].equals("settleup");
    }

    @Override
    public void execute(String command) {
        String words[]=command.split(",");
        if(words.length!=2){
            System.out.println("Invalid command. Usage: settleup,<userId1>,<userId2>");
            return;
        }

        SettleUpGroupRequestDTO requestDTO=new SettleUpGroupRequestDTO();
        requestDTO.setGroupId(Long.parseLong(words[1]));
        SettleUpGroupResponseDTO response =this.settleUpController.settleGroup(requestDTO);
        if(response!=null){
            if(response.getResponseStatus()== ResponseStatus.SUCCESS) {

            }
        }

    }
}
