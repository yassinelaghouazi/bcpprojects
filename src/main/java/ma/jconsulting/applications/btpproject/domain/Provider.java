package ma.jconsulting.applications.btpproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Provider.
 */
@Entity
@Table(name = "provider")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_provider", nullable = false)
    private Long idProvider;

    @Column(name = "provider_name")
    private String providerName;

    @Lob
    @Column(name = "provider_logo")
    private byte[] providerLogo;

    @Column(name = "provider_logo_content_type")
    private String providerLogoContentType;

    @ManyToOne
    @JsonIgnoreProperties("providers")
    private Orders orders;

    @ManyToOne
    @JsonIgnoreProperties("recommendedProviders")
    private Products products;

    @ManyToOne
    @JsonIgnoreProperties("providers")
    private Reglement reglement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProvider() {
        return idProvider;
    }

    public Provider idProvider(Long idProvider) {
        this.idProvider = idProvider;
        return this;
    }

    public void setIdProvider(Long idProvider) {
        this.idProvider = idProvider;
    }

    public String getProviderName() {
        return providerName;
    }

    public Provider providerName(String providerName) {
        this.providerName = providerName;
        return this;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public byte[] getProviderLogo() {
        return providerLogo;
    }

    public Provider providerLogo(byte[] providerLogo) {
        this.providerLogo = providerLogo;
        return this;
    }

    public void setProviderLogo(byte[] providerLogo) {
        this.providerLogo = providerLogo;
    }

    public String getProviderLogoContentType() {
        return providerLogoContentType;
    }

    public Provider providerLogoContentType(String providerLogoContentType) {
        this.providerLogoContentType = providerLogoContentType;
        return this;
    }

    public void setProviderLogoContentType(String providerLogoContentType) {
        this.providerLogoContentType = providerLogoContentType;
    }

    public Orders getOrders() {
        return orders;
    }

    public Provider orders(Orders orders) {
        this.orders = orders;
        return this;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public Provider products(Products products) {
        this.products = products;
        return this;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Reglement getReglement() {
        return reglement;
    }

    public Provider reglement(Reglement reglement) {
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
        if (!(o instanceof Provider)) {
            return false;
        }
        return id != null && id.equals(((Provider) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Provider{" +
            "id=" + getId() +
            ", idProvider=" + getIdProvider() +
            ", providerName='" + getProviderName() + "'" +
            ", providerLogo='" + getProviderLogo() + "'" +
            ", providerLogoContentType='" + getProviderLogoContentType() + "'" +
            "}";
    }
}
