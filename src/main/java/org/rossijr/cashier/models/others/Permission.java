package org.rossijr.cashier.models.others;

import jakarta.persistence.*;
import org.rossijr.cashier.models.organization.User;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tb_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<User> users;


    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
