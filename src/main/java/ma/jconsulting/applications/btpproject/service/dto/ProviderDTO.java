package ma.jconsulting.applications.btpproject.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Provider} entity.
 */
public class ProviderDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idProvider;

    private String providerName;

    @Lob
    private byte[] providerLogo;

    private String providerLogoContentType;

    private Long ordersId;

    private Long productsId;

    private Long reglementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Long idProvider) {
        this.idProvider = idProvider;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public byte[] getProviderLogo() {
        return providerLogo;
    }

    public void setProviderLogo(byte[] providerLogo) {
        this.providerLogo = providerLogo;
    }

    public String getProviderLogoContentType() {
        return providerLogoContentType;
    }

    public void setProviderLogoContentType(String providerLogoContentType) {
        this.providerLogoContentType = providerLogoContentType;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
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

        ProviderDTO providerDTO = (ProviderDTO) o;
        if (providerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), providerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProviderDTO{" +
            "id=" + getId() +
            ", idProvider=" + getIdProvider() +
            ", providerName='" + getProviderName() + "'" +
            ", providerLogo='" + getProviderLogo() + "'" +
            ", ordersId=" + getOrdersId() +
            ", productsId=" + getProductsId() +
            ", reglementId=" + getReglementId() +
            "}";
    }
}
