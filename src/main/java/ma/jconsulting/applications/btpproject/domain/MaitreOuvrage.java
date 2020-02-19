package ma.jconsulting.applications.btpproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MaitreOuvrage.
 */
@Entity
@Table(name = "maitre_ouvrage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MaitreOuvrage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_maitre_ouvrage")
    private Long idMaitreOuvrage;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "contact_personne")
    private String contactPersonne;

    @ManyToOne
    @JsonIgnoreProperties("maitreOuvrages")
    private Bopportunity bopportunity;

    @ManyToOne
    @JsonIgnoreProperties("maitreouvrages")
    private Caution caution;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMaitreOuvrage() {
        return idMaitreOuvrage;
    }

    public MaitreOuvrage idMaitreOuvrage(Long idMaitreOuvrage) {
        this.idMaitreOuvrage = idMaitreOuvrage;
        return this;
    }

    public void setIdMaitreOuvrage(Long idMaitreOuvrage) {
        this.idMaitreOuvrage = idMaitreOuvrage;
    }

    public String getNom() {
        return nom;
    }

    public MaitreOuvrage nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public MaitreOuvrage email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public MaitreOuvrage tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContactPersonne() {
        return contactPersonne;
    }

    public MaitreOuvrage contactPersonne(String contactPersonne) {
        this.contactPersonne = contactPersonne;
        return this;
    }

    public void setContactPersonne(String contactPersonne) {
        this.contactPersonne = contactPersonne;
    }

    public Bopportunity getBopportunity() {
        return bopportunity;
    }

    public MaitreOuvrage bopportunity(Bopportunity bopportunity) {
        this.bopportunity = bopportunity;
        return this;
    }

    public void setBopportunity(Bopportunity bopportunity) {
        this.bopportunity = bopportunity;
    }

    public Caution getCaution() {
        return caution;
    }

    public MaitreOuvrage caution(Caution caution) {
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
        if (!(o instanceof MaitreOuvrage)) {
            return false;
        }
        return id != null && id.equals(((MaitreOuvrage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MaitreOuvrage{" +
            "id=" + getId() +
            ", idMaitreOuvrage=" + getIdMaitreOuvrage() +
            ", nom='" + getNom() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel='" + getTel() + "'" +
            ", contactPersonne='" + getContactPersonne() + "'" +
            "}";
    }
}
