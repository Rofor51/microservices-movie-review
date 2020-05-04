package com.example.auth.authserver.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "role")
@Entity
public class Role implements Serializable {
    @Id
    @Column(name = "roleID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleID;

    @Column(name = "role")
    private String role;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "roles")
    private Set<User> users = new HashSet<>();


    public Role() {
    }


    public Role(String role) {
        this.role = role;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
