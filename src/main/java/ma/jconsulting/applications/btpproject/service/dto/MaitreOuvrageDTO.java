package ma.jconsulting.applications.btpproject.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.MaitreOuvrage} entity.
 */
public class MaitreOuvrageDTO implements Serializable {

    private Long id;

    private Long idMaitreOuvrage;

    private String nom;

    private String email;

    private String tel;

    private String contactPersonne;


    private Long bopportunityId;

    private Long cautionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMaitreOuvrage() {
        return idMaitreOuvrage;
    }

    public void setIdMaitreOuvrage(Long idMaitreOuvrage) {
        this.idMaitreOuvrage = idMaitreOuvrage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContactPersonne() {
        return contactPersonne;
    }

    public void setContactPersonne(String contactPersonne) {
        this.contactPersonne = contactPersonne;
    }

    public Long getBopportunityId() {
        return bopportunityId;
    }

    public void setBopportunityId(Long bopportunityId) {
        this.bopportunityId = bopportunityId;
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

        MaitreOuvrageDTO maitreOuvrageDTO = (MaitreOuvrageDTO) o;
        if (maitreOuvrageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), maitreOuvrageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MaitreOuvrageDTO{" +
            "id=" + getId() +
            ", idMaitreOuvrage=" + getIdMaitreOuvrage() +
            ", nom='" + getNom() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel='" + getTel() + "'" +
            ", contactPersonne='" + getContactPersonne() + "'" +
            ", bopportunityId=" + getBopportunityId() +
            ", cautionId=" + getCautionId() +
            "}";
    }
}
