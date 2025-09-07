package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.split.splitwise.command.expenses.AddExpenseCommand;
import org.split.splitwise.models.Expense;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class AddExpenseResponseDTO extends BaseResponseDTO{

    private long expenseId;
    private long createdUserId;
}
