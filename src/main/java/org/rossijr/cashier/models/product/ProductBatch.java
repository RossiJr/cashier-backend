package org.rossijr.cashier.models.product;

import jakarta.persistence.*;
import org.rossijr.cashier.models.organization.purchase.Purchase;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "tb_product_batch")
public class ProductBatch implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_inventory_id", nullable = false)
    private ProductInventory productInventory;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @Column(name = "COST_PER_PRODUCT")
    private Double costPerProduct;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PURCHASE_DATE", columnDefinition = "TIMESTAMP")
    private Calendar purchaseDate;

    @Column(name = "EXPIRATION_DATE", columnDefinition = "TIMESTAMP")
    private Calendar expirationDate;


    public ProductBatch() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductInventory getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(ProductInventory productInventory) {
        this.productInventory = productInventory;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Double getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(Double costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Calendar getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Calendar purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }
}
