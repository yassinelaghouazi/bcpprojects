package ma.jconsulting.applications.btpproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Services.
 */
@Entity
@Table(name = "services")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_services", nullable = false)
    private Long idServices;

    @Column(name = "services_desc")
    private String servicesDesc;

    @ManyToOne
    @JsonIgnoreProperties("services")
    private OrdersServices ordersServices;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdServices() {
        return idServices;
    }

    public Services idServices(Long idServices) {
        this.idServices = idServices;
        return this;
    }

    public void setIdServices(Long idServices) {
        this.idServices = idServices;
    }

    public String getServicesDesc() {
        return servicesDesc;
    }

    public Services servicesDesc(String servicesDesc) {
        this.servicesDesc = servicesDesc;
        return this;
    }

    public void setServicesDesc(String servicesDesc) {
        this.servicesDesc = servicesDesc;
    }

    public OrdersServices getOrdersServices() {
        return ordersServices;
    }

    public Services ordersServices(OrdersServices ordersServices) {
        this.ordersServices = ordersServices;
        return this;
    }

    public void setOrdersServices(OrdersServices ordersServices) {
        this.ordersServices = ordersServices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Services)) {
            return false;
        }
        return id != null && id.equals(((Services) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Services{" +
            "id=" + getId() +
            ", idServices=" + getIdServices() +
            ", servicesDesc='" + getServicesDesc() + "'" +
            "}";
    }
}
