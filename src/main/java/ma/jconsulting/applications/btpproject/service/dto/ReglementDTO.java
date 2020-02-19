package ma.jconsulting.applications.btpproject.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Reglement} entity.
 */
public class ReglementDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idReglement;

    private LocalDate dateEffetReglement;

    private LocalDate dateReglement;

    private Double montantReglement;

    private String commentaire;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdReglement() {
        return idReglement;
    }

    public void setIdReglement(Long idReglement) {
        this.idReglement = idReglement;
    }

    public LocalDate getDateEffetReglement() {
        return dateEffetReglement;
    }

    public void setDateEffetReglement(LocalDate dateEffetReglement) {
        this.dateEffetReglement = dateEffetReglement;
    }

    public LocalDate getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(LocalDate dateReglement) {
        this.dateReglement = dateReglement;
    }

    public Double getMontantReglement() {
        return montantReglement;
    }

    public void setMontantReglement(Double montantReglement) {
        this.montantReglement = montantReglement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReglementDTO reglementDTO = (ReglementDTO) o;
        if (reglementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reglementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReglementDTO{" +
            "id=" + getId() +
            ", idReglement=" + getIdReglement() +
            ", dateEffetReglement='" + getDateEffetReglement() + "'" +
            ", dateReglement='" + getDateReglement() + "'" +
            ", montantReglement=" + getMontantReglement() +
            ", commentaire='" + getCommentaire() + "'" +
            "}";
    }
}
