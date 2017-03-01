package com.notebook.entities;

import javax.persistence.*;

/**
 * Created by munna on 2/24/17.
 */
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private Long roleId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    public Role(){

    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
