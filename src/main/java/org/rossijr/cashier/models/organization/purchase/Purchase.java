package org.rossijr.cashier.models.organization.purchase;

import jakarta.persistence.*;
import org.rossijr.cashier.enums.organization.PurchaseStatus;
import org.rossijr.cashier.models.organization.Organization;
import org.rossijr.cashier.models.organization.User;
import org.rossijr.cashier.models.product.ProductBatch;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_purchase")
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "PURCHASE_DATE", columnDefinition = "TIMESTAMP")
    private Calendar purchaseDate;

    @OneToMany(mappedBy = "purchase")
    private List<ProductBatch> batches;

    @Column(name = "TOTAL_COST")
    private Double totalCost;

    @Column(name = "ADDITIONAL_INFO", columnDefinition = "TEXT")
    private String additionalInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PurchaseStatus status;

    @Column(name = "LAST_UPDATE", columnDefinition = "TIMESTAMP")
    private Calendar lastUpdateDate;


    public Purchase() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Calendar purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<ProductBatch> getBatches() {
        return batches;
    }

    public void setBatches(List<ProductBatch> batches) {
        this.batches = batches;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

    public Calendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Calendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
