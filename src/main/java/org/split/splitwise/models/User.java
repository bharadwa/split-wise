package org.split.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="users")
@Getter
@Setter
public class User extends BaseModel{

    private String name;

    private String email;

    private String phoneNumber;

    private String password;

    @OneToMany(fetch = FetchType.LAZY,mappedBy ="user")
    private List<Expense> expenses;
}
