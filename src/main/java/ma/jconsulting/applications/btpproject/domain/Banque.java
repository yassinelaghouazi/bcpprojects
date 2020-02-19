package ma.jconsulting.applications.btpproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Banque.
 */
@Entity
@Table(name = "banque")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Banque implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_banque", nullable = false)
    private Long idBanque;

    @NotNull
    @Column(name = "banque", nullable = false)
    private String banque;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_tel")
    private String contactTel;

    @Column(name = "adresse_agence")
    private String adresseAgence;

    @ManyToOne
    @JsonIgnoreProperties("banques")
    private Caution caution;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBanque() {
        return idBanque;
    }

    public Banque idBanque(Long idBanque) {
        this.idBanque = idBanque;
        return this;
    }

    public void setIdBanque(Long idBanque) {
        this.idBanque = idBanque;
    }

    public String getBanque() {
        return banque;
    }

    public Banque banque(String banque) {
        this.banque = banque;
        return this;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Banque contactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactTel() {
        return contactTel;
    }

    public Banque contactTel(String contactTel) {
        this.contactTel = contactTel;
        return this;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getAdresseAgence() {
        return adresseAgence;
    }

    public Banque adresseAgence(String adresseAgence) {
        this.adresseAgence = adresseAgence;
        return this;
    }

    public void setAdresseAgence(String adresseAgence) {
        this.adresseAgence = adresseAgence;
    }

    public Caution getCaution() {
        return caution;
    }

    public Banque caution(Caution caution) {
        this.caution = caution;
        return this;
    }

    public void setCaution(Caution caution) {
        this.caution = caution;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Banque)) {
            return false;
        }
        return id != null && id.equals(((Banque) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Banque{" +
            "id=" + getId() +
            ", idBanque=" + getIdBanque() +
            ", banque='" + getBanque() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", contactTel='" + getContactTel() + "'" +
            ", adresseAgence='" + getAdresseAgence() + "'" +
            "}";
    }
}
