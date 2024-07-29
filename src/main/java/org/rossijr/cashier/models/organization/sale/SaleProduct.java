package org.rossijr.cashier.models.organization.sale;

import jakarta.persistence.*;
import org.rossijr.cashier.models.product.ProductBatch;

import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "tb_sale_product")
public class SaleProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_batch_id", nullable = false)
    private ProductBatch productBatch;

    @Column(name = "PRICE_PER_PRODUCT")
    private Double pricePerProduct;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToOne
    private Sale sale;


    public SaleProduct() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductBatch getProductBatch() {
        return productBatch;
    }

    public void setProductBatch(ProductBatch productBatch) {
        this.productBatch = productBatch;
    }

    public Double getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(Double pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
