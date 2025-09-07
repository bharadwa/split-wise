package org.split.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="user_groups")
@Setter
@Getter
public class Group extends BaseModel{


    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="group_members",
            joinColumns = @JoinColumn(name="group_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> groupMembers;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "group")
    private List<Expense> expenses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_user_id")
    private User createBy;
}
