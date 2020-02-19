package ma.jconsulting.applications.btpproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Products.
 */
@Entity
@Table(name = "products")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_products", nullable = false)
    private Long idProducts;

    @Column(name = "products_desc")
    private String productsDesc;

    @Column(name = "recommended_price")
    private Double recommendedPrice;

    @OneToMany(mappedBy = "products")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Provider> recommendedProviders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("products")
    private OrdersProducts ordersProducts;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProducts() {
        return idProducts;
    }

    public Products idProducts(Long idProducts) {
        this.idProducts = idProducts;
        return this;
    }

    public void setIdProducts(Long idProducts) {
        this.idProducts = idProducts;
    }

    public String getProductsDesc() {
        return productsDesc;
    }

    public Products productsDesc(String productsDesc) {
        this.productsDesc = productsDesc;
        return this;
    }

    public void setProductsDesc(String productsDesc) {
        this.productsDesc = productsDesc;
    }

    public Double getRecommendedPrice() {
        return recommendedPrice;
    }

    public Products recommendedPrice(Double recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
        return this;
    }

    public void setRecommendedPrice(Double recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }

    public Set<Provider> getRecommendedProviders() {
        return recommendedProviders;
    }

    public Products recommendedProviders(Set<Provider> providers) {
        this.recommendedProviders = providers;
        return this;
    }

    public Products addRecommendedProvider(Provider provider) {
        this.recommendedProviders.add(provider);
        provider.setProducts(this);
        return this;
    }

    public Products removeRecommendedProvider(Provider provider) {
        this.recommendedProviders.remove(provider);
        provider.setProducts(null);
        return this;
    }

    public void setRecommendedProviders(Set<Provider> providers) {
        this.recommendedProviders = providers;
    }

    public OrdersProducts getOrdersProducts() {
        return ordersProducts;
    }

    public Products ordersProducts(OrdersProducts ordersProducts) {
        this.ordersProducts = ordersProducts;
        return this;
    }

    public void setOrdersProducts(OrdersProducts ordersProducts) {
        this.ordersProducts = ordersProducts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Products)) {
            return false;
        }
        return id != null && id.equals(((Products) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Products{" +
            "id=" + getId() +
            ", idProducts=" + getIdProducts() +
            ", productsDesc='" + getProductsDesc() + "'" +
            ", recommendedPrice=" + getRecommendedPrice() +
            "}";
    }
}
