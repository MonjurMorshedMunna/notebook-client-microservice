package com.notebook.entities;

import javax.persistence.*;

/**
 * Created by munna on 2/24/17.
 */
@Entity
@Table(name="user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_role_id")
    private Long userRoleId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="role_id")
    private Role role;

    public UserRole(){

    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
