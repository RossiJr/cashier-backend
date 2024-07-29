package org.rossijr.cashier.models.organization;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.rossijr.cashier.enums.organization.Status;
import org.rossijr.cashier.models.organization.purchase.Purchase;
import org.rossijr.cashier.models.others.Address;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_organization")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "NAME", nullable = false, columnDefinition = "TEXT")
    private String name;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    private Address address;

    @Column(name = "EMAIL")
    private String organizationEmail;

    @Column(name = "PHONE_NUMBER")
    private String organizationPhoneNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Inventory> inventories = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
    private Calendar creationDate;

    @Enumerated(EnumType.STRING) @Column(name = "STATUS")
    private Status status;

    @Column(name = "MAX_USERS")
    private Integer maxUsers;

    @Column(name = "MAX_INVENTORIES")
    private Integer maxInventories;


    public Organization() {
    }

    public Organization(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    public String getOrganizationPhoneNumber() {
        return organizationPhoneNumber;
    }

    public void setOrganizationPhoneNumber(String organizationPhoneNumber) {
        this.organizationPhoneNumber = organizationPhoneNumber;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Integer getMaxInventories() {
        return maxInventories;
    }

    public void setMaxInventories(Integer maxInventories) {
        this.maxInventories = maxInventories;
    }
}
