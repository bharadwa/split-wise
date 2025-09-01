package org.split.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name="expenses")
public class Expense extends BaseModel{

    private double amount;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Groups group;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

}
