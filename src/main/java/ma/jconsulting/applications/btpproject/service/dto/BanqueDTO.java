package ma.jconsulting.applications.btpproject.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Banque} entity.
 */
public class BanqueDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idBanque;

    @NotNull
    private String banque;

    private String contactEmail;

    private String contactTel;

    private String adresseAgence;


    private Long cautionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(Long idBanque) {
        this.idBanque = idBanque;
    }

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getAdresseAgence() {
        return adresseAgence;
    }

    public void setAdresseAgence(String adresseAgence) {
        this.adresseAgence = adresseAgence;
    }

    public Long getCautionId() {
        return cautionId;
    }

    public void setCautionId(Long cautionId) {
        this.cautionId = cautionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BanqueDTO banqueDTO = (BanqueDTO) o;
        if (banqueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), banqueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BanqueDTO{" +
            "id=" + getId() +
            ", idBanque=" + getIdBanque() +
            ", banque='" + getBanque() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", contactTel='" + getContactTel() + "'" +
            ", adresseAgence='" + getAdresseAgence() + "'" +
            ", cautionId=" + getCautionId() +
            "}";
    }
}
