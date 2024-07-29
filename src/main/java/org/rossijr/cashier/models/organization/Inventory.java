package org.rossijr.cashier.models.organization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.rossijr.cashier.models.product.ProductInventory;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_inventory")
public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "NAME", nullable = false, columnDefinition = "TEXT")
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<ProductInventory> productInventories;

    @Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP")
    private Calendar createdAt;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;


    public Inventory() {
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<ProductInventory> getProductInventories() {
        return productInventories;
    }

    public void setProductInventories(List<ProductInventory> productInventories) {
        this.productInventories = productInventories;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
