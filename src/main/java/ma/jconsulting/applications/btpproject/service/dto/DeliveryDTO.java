package ma.jconsulting.applications.btpproject.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Delivery} entity.
 */
public class DeliveryDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idDelivery;

    private String deliveryDescription;

    private LocalDate deliveryDate;

    private Double subTotal;

    private Double vatamount;

    private Double total;


    private Long reglementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
    }

    public String getDeliveryDescription() {
        return deliveryDescription;
    }

    public void setDeliveryDescription(String deliveryDescription) {
        this.deliveryDescription = deliveryDescription;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getVatamount() {
        return vatamount;
    }

    public void setVatamount(Double vatamount) {
        this.vatamount = vatamount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getReglementId() {
        return reglementId;
    }

    public void setReglementId(Long reglementId) {
        this.reglementId = reglementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeliveryDTO deliveryDTO = (DeliveryDTO) o;
        if (deliveryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" +
            "id=" + getId() +
            ", idDelivery=" + getIdDelivery() +
            ", deliveryDescription='" + getDeliveryDescription() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", subTotal=" + getSubTotal() +
            ", vatamount=" + getVatamount() +
            ", total=" + getTotal() +
            ", reglementId=" + getReglementId() +
            "}";
    }
}
