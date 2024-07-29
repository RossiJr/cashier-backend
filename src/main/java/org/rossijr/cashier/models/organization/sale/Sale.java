package org.rossijr.cashier.models.organization.sale;

import jakarta.persistence.*;
import org.rossijr.cashier.models.organization.User;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_sale")
public class Sale implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleProduct> productsSold;

    @Column(name = "SALE_DATE", columnDefinition = "TIMESTAMP")
    private Calendar saleDate;

    @Column(name = "DISCOUNT")
    private Double discount;

    @Column(name = "TOTAL_PRICE_BEFORE_DISCOUNT")
    private Double totalPriceBeforeDiscount;


    public Sale() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SaleProduct> getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(List<SaleProduct> productsSold) {
        this.productsSold = productsSold;
    }

    public Calendar getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Calendar saleDate) {
        this.saleDate = saleDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public void setTotalPriceBeforeDiscount(Double totalPriceBeforeDiscount) {
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
    }
}
