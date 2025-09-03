package org.split.splitwise.controllers;

import org.split.splitwise.dtos.ResponseStatus;
import org.split.splitwise.dtos.SettleUpGroupRequestDTO;
import org.split.splitwise.dtos.SettleUpGroupResponseDTO;
import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.services.SettleUpGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SettleUpController {

    private final SettleUpGroupService settleUpGroupService;

    @Autowired
    public SettleUpController(SettleUpGroupService settleUpGroupService) {
        this.settleUpGroupService = settleUpGroupService;
    }

    public SettleUpGroupResponseDTO settleGroup(SettleUpGroupRequestDTO settleUpGroupRequestDTO) {
        SettleUpGroupResponseDTO response =new SettleUpGroupResponseDTO();
        try {
            response.setTransactions(this.settleUpGroupService.settleUpGroup(settleUpGroupRequestDTO.getGroupId()));
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (GroupNotFoundException e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;

    }


}
