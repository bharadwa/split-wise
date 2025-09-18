package org.split.splitwise.controllers;

import org.split.splitwise.dtos.AddExpenseRequestDTO;
import org.split.splitwise.dtos.AddExpenseResponseDTO;
import org.split.splitwise.dtos.ResponseStatus;
import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.exceptions.UserNotFoundException;
import org.split.splitwise.models.Expense;
import org.split.splitwise.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenseController {


    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /*public AddExpenseResponseDTO addExpense(AddExpenseRequestDTO addExpenseRequestDTO) {
        AddExpenseResponseDTO responseDTO = new AddExpenseResponseDTO();

        try {
            Expense expense = this.expenseService.addExpense(addExpenseRequestDTO.getCreatedUserId(), addExpenseRequestDTO.getGroupId(), addExpenseRequestDTO.getWhoPaid(), addExpenseRequestDTO.getWhoHasToPay());
            responseDTO.setExpenseId(expense.getId());
            responseDTO.setCreatedUserId(expense.getCreatedBy().getId());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        } catch (UserNotFoundException | GroupNotFoundException e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setResponseMessage(e.getMessage());
        }

        return responseDTO;

    }*/


}
