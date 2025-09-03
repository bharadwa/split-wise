package org.split.splitwise.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name="expenses")
public class Expense extends BaseModel{

    private double amount;

    private String description;

    private Date expenseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Groups group;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    private UserExpenseType expenseType;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "expense")
    private List<UserExpense> paidByUsers;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "expense")
    private List<UserExpense> paidForUsers;

}
