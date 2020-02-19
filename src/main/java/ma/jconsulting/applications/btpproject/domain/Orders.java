package ma.jconsulting.applications.btpproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Orders.
 */
@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_orders", nullable = false)
    private Long idOrders;

    @Column(name = "orders_desc")
    private String ordersDesc;

    @Column(name = "totalht")
    private Double totalht;

    @Column(name = "totaltva")
    private Double totaltva;

    @Column(name = "totalttc")
    private Double totalttc;

    @Column(name = "orders_date")
    private LocalDate ordersDate;

    @Column(name = "expected_delivery")
    private LocalDate expectedDelivery;

    @Column(name = "tvamoyenne")
    private Double tvamoyenne;

    @OneToMany(mappedBy = "orders")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "orders")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Provider> providers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private Delivery delivery;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private OrdersProducts ordersProducts;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private OrdersServices ordersServices;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private Reglement reglement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrders() {
        return idOrders;
    }

    public Orders idOrders(Long idOrders) {
        this.idOrders = idOrders;
        return this;
    }

    public void setIdOrders(Long idOrders) {
        this.idOrders = idOrders;
    }

    public String getOrdersDesc() {
        return ordersDesc;
    }

    public Orders ordersDesc(String ordersDesc) {
        this.ordersDesc = ordersDesc;
        return this;
    }

    public void setOrdersDesc(String ordersDesc) {
        this.ordersDesc = ordersDesc;
    }

    public Double getTotalht() {
        return totalht;
    }

    public Orders totalht(Double totalht) {
        this.totalht = totalht;
        return this;
    }

    public void setTotalht(Double totalht) {
        this.totalht = totalht;
    }

    public Double getTotaltva() {
        return totaltva;
    }

    public Orders totaltva(Double totaltva) {
        this.totaltva = totaltva;
        return this;
    }

    public void setTotaltva(Double totaltva) {
        this.totaltva = totaltva;
    }

    public Double getTotalttc() {
        return totalttc;
    }

    public Orders totalttc(Double totalttc) {
        this.totalttc = totalttc;
        return this;
    }

    public void setTotalttc(Double totalttc) {
        this.totalttc = totalttc;
    }

    public LocalDate getOrdersDate() {
        return ordersDate;
    }

    public Orders ordersDate(LocalDate ordersDate) {
        this.ordersDate = ordersDate;
        return this;
    }

    public void setOrdersDate(LocalDate ordersDate) {
        this.ordersDate = ordersDate;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public Orders expectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
        return this;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public Double getTvamoyenne() {
        return tvamoyenne;
    }

    public Orders tvamoyenne(Double tvamoyenne) {
        this.tvamoyenne = tvamoyenne;
        return this;
    }

    public void setTvamoyenne(Double tvamoyenne) {
        this.tvamoyenne = tvamoyenne;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Orders projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Orders addProject(Project project) {
        this.projects.add(project);
        project.setOrders(this);
        return this;
    }

    public Orders removeProject(Project project) {
        this.projects.remove(project);
        project.setOrders(null);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Provider> getProviders() {
        return providers;
    }

    public Orders providers(Set<Provider> providers) {
        this.providers = providers;
        return this;
    }

    public Orders addProvider(Provider provider) {
        this.providers.add(provider);
        provider.setOrders(this);
        return this;
    }

    public Orders removeProvider(Provider provider) {
        this.providers.remove(provider);
        provider.setOrders(null);
        return this;
    }

    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Orders delivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public OrdersProducts getOrdersProducts() {
        return ordersProducts;
    }

    public Orders ordersProducts(OrdersProducts ordersProducts) {
        this.ordersProducts = ordersProducts;
        return this;
    }

    public void setOrdersProducts(OrdersProducts ordersProducts) {
        this.ordersProducts = ordersProducts;
    }

    public OrdersServices getOrdersServices() {
        return ordersServices;
    }

    public Orders ordersServices(OrdersServices ordersServices) {
        this.ordersServices = ordersServices;
        return this;
    }

    public void setOrdersServices(OrdersServices ordersServices) {
        this.ordersServices = ordersServices;
    }

    public Reglement getReglement() {
        return reglement;
    }

    public Orders reglement(Reglement reglement) {
        this.reglement = reglement;
        return this;
    }

    public void setReglement(Reglement reglement) {
        this.reglement = reglement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Orders)) {
            return false;
        }
        return id != null && id.equals(((Orders) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Orders{" +
            "id=" + getId() +
            ", idOrders=" + getIdOrders() +
            ", ordersDesc='" + getOrdersDesc() + "'" +
            ", totalht=" + getTotalht() +
            ", totaltva=" + getTotaltva() +
            ", totalttc=" + getTotalttc() +
            ", ordersDate='" + getOrdersDate() + "'" +
            ", expectedDelivery='" + getExpectedDelivery() + "'" +
            ", tvamoyenne=" + getTvamoyenne() +
            "}";
    }
}
