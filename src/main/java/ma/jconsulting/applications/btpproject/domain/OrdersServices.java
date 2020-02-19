package ma.jconsulting.applications.btpproject.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrdersServices.
 */
@Entity
@Table(name = "orders_services")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrdersServices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_orders_services", nullable = false)
    private Long idOrdersServices;

    @Column(name = "tarif_journalier")
    private Double tarifJournalier;

    @Column(name = "jours_homme")
    private Double joursHomme;

    @OneToMany(mappedBy = "ordersServices")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Orders> orders = new HashSet<>();

    @OneToMany(mappedBy = "ordersServices")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Services> services = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrdersServices() {
        return idOrdersServices;
    }

    public OrdersServices idOrdersServices(Long idOrdersServices) {
        this.idOrdersServices = idOrdersServices;
        return this;
    }

    public void setIdOrdersServices(Long idOrdersServices) {
        this.idOrdersServices = idOrdersServices;
    }

    public Double getTarifJournalier() {
        return tarifJournalier;
    }

    public OrdersServices tarifJournalier(Double tarifJournalier) {
        this.tarifJournalier = tarifJournalier;
        return this;
    }

    public void setTarifJournalier(Double tarifJournalier) {
        this.tarifJournalier = tarifJournalier;
    }

    public Double getJoursHomme() {
        return joursHomme;
    }

    public OrdersServices joursHomme(Double joursHomme) {
        this.joursHomme = joursHomme;
        return this;
    }

    public void setJoursHomme(Double joursHomme) {
        this.joursHomme = joursHomme;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public OrdersServices orders(Set<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public OrdersServices addOrders(Orders orders) {
        this.orders.add(orders);
        orders.setOrdersServices(this);
        return this;
    }

    public OrdersServices removeOrders(Orders orders) {
        this.orders.remove(orders);
        orders.setOrdersServices(null);
        return this;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<Services> getServices() {
        return services;
    }

    public OrdersServices services(Set<Services> services) {
        this.services = services;
        return this;
    }

    public OrdersServices addServices(Services services) {
        this.services.add(services);
        services.setOrdersServices(this);
        return this;
    }

    public OrdersServices removeServices(Services services) {
        this.services.remove(services);
        services.setOrdersServices(null);
        return this;
    }

    public void setServices(Set<Services> services) {
        this.services = services;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrdersServices)) {
            return false;
        }
        return id != null && id.equals(((OrdersServices) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrdersServices{" +
            "id=" + getId() +
            ", idOrdersServices=" + getIdOrdersServices() +
            ", tarifJournalier=" + getTarifJournalier() +
            ", joursHomme=" + getJoursHomme() +
            "}";
    }
}
