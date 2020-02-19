package ma.jconsulting.applications.btpproject.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Products} entity.
 */
public class ProductsDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idProducts;

    private String productsDesc;

    private Double recommendedPrice;


    private Long ordersProductsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(Long idProducts) {
        this.idProducts = idProducts;
    }

    public String getProductsDesc() {
        return productsDesc;
    }

    public void setProductsDesc(String productsDesc) {
        this.productsDesc = productsDesc;
    }

    public Double getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(Double recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }

    public Long getOrdersProductsId() {
        return ordersProductsId;
    }

    public void setOrdersProductsId(Long ordersProductsId) {
        this.ordersProductsId = ordersProductsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductsDTO productsDTO = (ProductsDTO) o;
        if (productsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
            "id=" + getId() +
            ", idProducts=" + getIdProducts() +
            ", productsDesc='" + getProductsDesc() + "'" +
            ", recommendedPrice=" + getRecommendedPrice() +
            ", ordersProductsId=" + getOrdersProductsId() +
            "}";
    }
}
