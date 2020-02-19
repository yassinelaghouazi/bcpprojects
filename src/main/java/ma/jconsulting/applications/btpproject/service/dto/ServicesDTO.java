package ma.jconsulting.applications.btpproject.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Services} entity.
 */
public class ServicesDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idServices;

    private String servicesDesc;


    private Long ordersServicesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdServices() {
        return idServices;
    }

    public void setIdServices(Long idServices) {
        this.idServices = idServices;
    }

    public String getServicesDesc() {
        return servicesDesc;
    }

    public void setServicesDesc(String servicesDesc) {
        this.servicesDesc = servicesDesc;
    }

    public Long getOrdersServicesId() {
        return ordersServicesId;
    }

    public void setOrdersServicesId(Long ordersServicesId) {
        this.ordersServicesId = ordersServicesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServicesDTO servicesDTO = (ServicesDTO) o;
        if (servicesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), servicesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ServicesDTO{" +
            "id=" + getId() +
            ", idServices=" + getIdServices() +
            ", servicesDesc='" + getServicesDesc() + "'" +
            ", ordersServicesId=" + getOrdersServicesId() +
            "}";
    }
}
