package ma.jconsulting.applications.btpproject.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrdersProducts.
 */
@Entity
@Table(name = "orders_products")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrdersProducts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_orders_products", nullable = false)
    private Long idOrdersProducts;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "quantite")
    private Double quantite;

    @Column(name = "tva")
    private Double tva;

    @Column(name = "total_ht")
    private Double totalHT;

    @Column(name = "montant_tva")
    private Double montantTVA;

    @Column(name = "total_ttc")
    private Double totalTTC;

    @OneToMany(mappedBy = "ordersProducts")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Orders> orders = new HashSet<>();

    @OneToMany(mappedBy = "ordersProducts")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Products> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrdersProducts() {
        return idOrdersProducts;
    }

    public OrdersProducts idOrdersProducts(Long idOrdersProducts) {
        this.idOrdersProducts = idOrdersProducts;
        return this;
    }

    public void setIdOrdersProducts(Long idOrdersProducts) {
        this.idOrdersProducts = idOrdersProducts;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public OrdersProducts unitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQuantite() {
        return quantite;
    }

    public OrdersProducts quantite(Double quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getTva() {
        return tva;
    }

    public OrdersProducts tva(Double tva) {
        this.tva = tva;
        return this;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public Double getTotalHT() {
        return totalHT;
    }

    public OrdersProducts totalHT(Double totalHT) {
        this.totalHT = totalHT;
        return this;
    }

    public void setTotalHT(Double totalHT) {
        this.totalHT = totalHT;
    }

    public Double getMontantTVA() {
        return montantTVA;
    }

    public OrdersProducts montantTVA(Double montantTVA) {
        this.montantTVA = montantTVA;
        return this;
    }

    public void setMontantTVA(Double montantTVA) {
        this.montantTVA = montantTVA;
    }

    public Double getTotalTTC() {
        return totalTTC;
    }

    public OrdersProducts totalTTC(Double totalTTC) {
        this.totalTTC = totalTTC;
        return this;
    }

    public void setTotalTTC(Double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public OrdersProducts orders(Set<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public OrdersProducts addOrders(Orders orders) {
        this.orders.add(orders);
        orders.setOrdersProducts(this);
        return this;
    }

    public OrdersProducts removeOrders(Orders orders) {
        this.orders.remove(orders);
        orders.setOrdersProducts(null);
        return this;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public OrdersProducts products(Set<Products> products) {
        this.products = products;
        return this;
    }

    public OrdersProducts addProducts(Products products) {
        this.products.add(products);
        products.setOrdersProducts(this);
        return this;
    }

    public OrdersProducts removeProducts(Products products) {
        this.products.remove(products);
        products.setOrdersProducts(null);
        return this;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrdersProducts)) {
            return false;
        }
        return id != null && id.equals(((OrdersProducts) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrdersProducts{" +
            "id=" + getId() +
            ", idOrdersProducts=" + getIdOrdersProducts() +
            ", unitPrice=" + getUnitPrice() +
            ", quantite=" + getQuantite() +
            ", tva=" + getTva() +
            ", totalHT=" + getTotalHT() +
            ", montantTVA=" + getMontantTVA() +
            ", totalTTC=" + getTotalTTC() +
            "}";
    }
}
