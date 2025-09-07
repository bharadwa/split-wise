package org.split.splitwise.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="user_expense_details")
public class UserExpense extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private double amount;

    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Expense expense;


}
