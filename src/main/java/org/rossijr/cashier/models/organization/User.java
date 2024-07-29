package org.rossijr.cashier.models.organization;

import jakarta.persistence.*;
import org.rossijr.cashier.enums.organization.Status;
import org.rossijr.cashier.models.organization.sale.Sale;
import org.rossijr.cashier.models.others.Address;
import org.rossijr.cashier.models.others.Permission;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "USERNAME", nullable = false, columnDefinition = "TEXT", unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "EMAIL", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "PHONE_NUMBER", columnDefinition = "TEXT")
    private String phoneNumber;

    @Column(name = "FIRST_NAME", columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "LAST_NAME", columnDefinition = "TEXT")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
    private Calendar creationDate;

    @ManyToMany
    @JoinTable(
            name = "tb_mm_user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    @Column(name = "LAST_LOGIN", columnDefinition = "TIMESTAMP")
    private Calendar lastLogin;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Sale> sales;


    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Calendar getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Calendar lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
