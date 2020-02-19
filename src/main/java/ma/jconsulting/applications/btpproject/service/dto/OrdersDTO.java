package ma.jconsulting.applications.btpproject.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Orders} entity.
 */
public class OrdersDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idOrders;

    private String ordersDesc;

    private Double totalht;

    private Double totaltva;

    private Double totalttc;

    private LocalDate ordersDate;

    private LocalDate expectedDelivery;

    private Double tvamoyenne;


    private Long deliveryId;

    private Long ordersProductsId;

    private Long ordersServicesId;

    private Long reglementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(Long idOrders) {
        this.idOrders = idOrders;
    }

    public String getOrdersDesc() {
        return ordersDesc;
    }

    public void setOrdersDesc(String ordersDesc) {
        this.ordersDesc = ordersDesc;
    }

    public Double getTotalht() {
        return totalht;
    }

    public void setTotalht(Double totalht) {
        this.totalht = totalht;
    }

    public Double getTotaltva() {
        return totaltva;
    }

    public void setTotaltva(Double totaltva) {
        this.totaltva = totaltva;
    }

    public Double getTotalttc() {
        return totalttc;
    }

    public void setTotalttc(Double totalttc) {
        this.totalttc = totalttc;
    }

    public LocalDate getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(LocalDate ordersDate) {
        this.ordersDate = ordersDate;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public Double getTvamoyenne() {
        return tvamoyenne;
    }

    public void setTvamoyenne(Double tvamoyenne) {
        this.tvamoyenne = tvamoyenne;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getOrdersProductsId() {
        return ordersProductsId;
    }

    public void setOrdersProductsId(Long ordersProductsId) {
        this.ordersProductsId = ordersProductsId;
    }

    public Long getOrdersServicesId() {
        return ordersServicesId;
    }

    public void setOrdersServicesId(Long ordersServicesId) {
        this.ordersServicesId = ordersServicesId;
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

        OrdersDTO ordersDTO = (OrdersDTO) o;
        if (ordersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ordersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
            "id=" + getId() +
            ", idOrders=" + getIdOrders() +
            ", ordersDesc='" + getOrdersDesc() + "'" +
            ", totalht=" + getTotalht() +
            ", totaltva=" + getTotaltva() +
            ", totalttc=" + getTotalttc() +
            ", ordersDate='" + getOrdersDate() + "'" +
            ", expectedDelivery='" + getExpectedDelivery() + "'" +
            ", tvamoyenne=" + getTvamoyenne() +
            ", deliveryId=" + getDeliveryId() +
            ", ordersProductsId=" + getOrdersProductsId() +
            ", ordersServicesId=" + getOrdersServicesId() +
            ", reglementId=" + getReglementId() +
            "}";
    }
}
