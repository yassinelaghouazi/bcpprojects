package ma.jconsulting.applications.btpproject.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.OrdersProducts} entity.
 */
public class OrdersProductsDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idOrdersProducts;

    private Double unitPrice;

    private Double quantite;

    private Double tva;

    private Double totalHT;

    private Double montantTVA;

    private Double totalTTC;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrdersProducts() {
        return idOrdersProducts;
    }

    public void setIdOrdersProducts(Long idOrdersProducts) {
        this.idOrdersProducts = idOrdersProducts;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getTva() {
        return tva;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public Double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(Double totalHT) {
        this.totalHT = totalHT;
    }

    public Double getMontantTVA() {
        return montantTVA;
    }

    public void setMontantTVA(Double montantTVA) {
        this.montantTVA = montantTVA;
    }

    public Double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(Double totalTTC) {
        this.totalTTC = totalTTC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrdersProductsDTO ordersProductsDTO = (OrdersProductsDTO) o;
        if (ordersProductsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ordersProductsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrdersProductsDTO{" +
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
