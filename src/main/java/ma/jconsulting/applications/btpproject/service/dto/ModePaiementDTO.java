package ma.jconsulting.applications.btpproject.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.ModePaiement} entity.
 */
public class ModePaiementDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idModePaiement;

    private String modePaiement;


    private Long reglementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdModePaiement() {
        return idModePaiement;
    }

    public void setIdModePaiement(Long idModePaiement) {
        this.idModePaiement = idModePaiement;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
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

        ModePaiementDTO modePaiementDTO = (ModePaiementDTO) o;
        if (modePaiementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), modePaiementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ModePaiementDTO{" +
            "id=" + getId() +
            ", idModePaiement=" + getIdModePaiement() +
            ", modePaiement='" + getModePaiement() + "'" +
            ", reglementId=" + getReglementId() +
            "}";
    }
}
