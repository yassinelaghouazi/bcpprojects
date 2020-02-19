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
 * A Delivery.
 */
@Entity
@Table(name = "delivery")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_delivery", nullable = false)
    private Long idDelivery;

    @Column(name = "delivery_description")
    private String deliveryDescription;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "vatamount")
    private Double vatamount;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "delivery")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "delivery")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Orders> orders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("deliveries")
    private Reglement reglement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDelivery() {
        return idDelivery;
    }

    public Delivery idDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
        return this;
    }

    public void setIdDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
    }

    public String getDeliveryDescription() {
        return deliveryDescription;
    }

    public Delivery deliveryDescription(String deliveryDescription) {
        this.deliveryDescription = deliveryDescription;
        return this;
    }

    public void setDeliveryDescription(String deliveryDescription) {
        this.deliveryDescription = deliveryDescription;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public Delivery deliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public Delivery subTotal(Double subTotal) {
        this.subTotal = subTotal;
        return this;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getVatamount() {
        return vatamount;
    }

    public Delivery vatamount(Double vatamount) {
        this.vatamount = vatamount;
        return this;
    }

    public void setVatamount(Double vatamount) {
        this.vatamount = vatamount;
    }

    public Double getTotal() {
        return total;
    }

    public Delivery total(Double total) {
        this.total = total;
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Delivery projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Delivery addProject(Project project) {
        this.projects.add(project);
        project.setDelivery(this);
        return this;
    }

    public Delivery removeProject(Project project) {
        this.projects.remove(project);
        project.setDelivery(null);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public Delivery orders(Set<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public Delivery addOrders(Orders orders) {
        this.orders.add(orders);
        orders.setDelivery(this);
        return this;
    }

    public Delivery removeOrders(Orders orders) {
        this.orders.remove(orders);
        orders.setDelivery(null);
        return this;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Reglement getReglement() {
        return reglement;
    }

    public Delivery reglement(Reglement reglement) {
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
        if (!(o instanceof Delivery)) {
            return false;
        }
        return id != null && id.equals(((Delivery) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Delivery{" +
            "id=" + getId() +
            ", idDelivery=" + getIdDelivery() +
            ", deliveryDescription='" + getDeliveryDescription() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", subTotal=" + getSubTotal() +
            ", vatamount=" + getVatamount() +
            ", total=" + getTotal() +
            "}";
    }
}
