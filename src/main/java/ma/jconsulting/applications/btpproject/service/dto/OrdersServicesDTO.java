package ma.jconsulting.applications.btpproject.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.OrdersServices} entity.
 */
public class OrdersServicesDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idOrdersServices;

    private Double tarifJournalier;

    private Double joursHomme;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrdersServices() {
        return idOrdersServices;
    }

    public void setIdOrdersServices(Long idOrdersServices) {
        this.idOrdersServices = idOrdersServices;
    }

    public Double getTarifJournalier() {
        return tarifJournalier;
    }

    public void setTarifJournalier(Double tarifJournalier) {
        this.tarifJournalier = tarifJournalier;
    }

    public Double getJoursHomme() {
        return joursHomme;
    }

    public void setJoursHomme(Double joursHomme) {
        this.joursHomme = joursHomme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrdersServicesDTO ordersServicesDTO = (OrdersServicesDTO) o;
        if (ordersServicesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ordersServicesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrdersServicesDTO{" +
            "id=" + getId() +
            ", idOrdersServices=" + getIdOrdersServices() +
            ", tarifJournalier=" + getTarifJournalier() +
            ", joursHomme=" + getJoursHomme() +
            "}";
    }
}
