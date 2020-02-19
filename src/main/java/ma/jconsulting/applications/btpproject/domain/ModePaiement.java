package ma.jconsulting.applications.btpproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ModePaiement.
 */
@Entity
@Table(name = "mode_paiement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ModePaiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_mode_paiement", nullable = false)
    private Long idModePaiement;

    @Column(name = "mode_paiement")
    private String modePaiement;

    @ManyToOne
    @JsonIgnoreProperties("modePaiements")
    private Reglement reglement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdModePaiement() {
        return idModePaiement;
    }

    public ModePaiement idModePaiement(Long idModePaiement) {
        this.idModePaiement = idModePaiement;
        return this;
    }

    public void setIdModePaiement(Long idModePaiement) {
        this.idModePaiement = idModePaiement;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public ModePaiement modePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
        return this;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Reglement getReglement() {
        return reglement;
    }

    public ModePaiement reglement(Reglement reglement) {
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
        if (!(o instanceof ModePaiement)) {
            return false;
        }
        return id != null && id.equals(((ModePaiement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ModePaiement{" +
            "id=" + getId() +
            ", idModePaiement=" + getIdModePaiement() +
            ", modePaiement='" + getModePaiement() + "'" +
            "}";
    }
}
