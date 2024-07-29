package org.rossijr.cashier.models.product;

import jakarta.persistence.*;
import org.rossijr.cashier.models.organization.Inventory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_product_inventory")
public class ProductInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "SUGGESTED_PRICE")
    private Double suggestedPrice;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @OneToMany(mappedBy = "productInventory", cascade = CascadeType.ALL)
    private List<ProductBatch> batches;

    @Column(name = "REGISTER_DATE", columnDefinition = "TIMESTAMP")
    private Date registerDate;


    public ProductInventory() {
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

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(Double suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<ProductBatch> getBatches() {
        return batches;
    }

    public void setBatches(List<ProductBatch> batches) {
        this.batches = batches;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
