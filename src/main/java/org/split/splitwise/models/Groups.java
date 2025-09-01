package org.split.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="groups")
@Setter
@Getter
public class Groups extends BaseModel{


    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="users_groups")
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "group")
    private List<Expense> expenses;
}
