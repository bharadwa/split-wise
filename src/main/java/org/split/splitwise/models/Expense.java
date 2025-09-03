package org.split.splitwise.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="expenses")
public class Expense extends BaseModel{

    private double amount;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Groups group;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    private UserExpenseType expenseType;

}
