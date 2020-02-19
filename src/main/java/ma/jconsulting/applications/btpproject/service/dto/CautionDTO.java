package ma.jconsulting.applications.btpproject.service.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import ma.jconsulting.applications.btpproject.domain.enumeration.StatusCaution;
import ma.jconsulting.applications.btpproject.domain.enumeration.TypeCaution;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Caution} entity.
 */
public class CautionDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idcaution;

    private Double montantCaution;

    private String objet;

    private String numeroCaution;

    private LocalDate dateDemande;

    private LocalDate dateRetrait;

    private LocalDate dateDepot;

    private String numeroMarche;

    private StatusCaution statusCaution;

    private TypeCaution typeCaution;

    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdcaution() {
        return idcaution;
    }

    public void setIdcaution(Long idcaution) {
        this.idcaution = idcaution;
    }

    public Double getMontantCaution() {
        return montantCaution;
    }

    public void setMontantCaution(Double montantCaution) {
        this.montantCaution = montantCaution;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getNumeroCaution() {
        return numeroCaution;
    }

    public void setNumeroCaution(String numeroCaution) {
        this.numeroCaution = numeroCaution;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalDate getDateRetrait() {
        return dateRetrait;
    }

    public void setDateRetrait(LocalDate dateRetrait) {
        this.dateRetrait = dateRetrait;
    }

    public LocalDate getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(LocalDate dateDepot) {
        this.dateDepot = dateDepot;
    }

    public String getNumeroMarche() {
        return numeroMarche;
    }

    public void setNumeroMarche(String numeroMarche) {
        this.numeroMarche = numeroMarche;
    }

    public StatusCaution getStatusCaution() {
        return statusCaution;
    }

    public void setStatusCaution(StatusCaution statusCaution) {
        this.statusCaution = statusCaution;
    }

    public TypeCaution getTypeCaution() {
        return typeCaution;
    }

    public void setTypeCaution(TypeCaution typeCaution) {
        this.typeCaution = typeCaution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CautionDTO cautionDTO = (CautionDTO) o;
        if (cautionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cautionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CautionDTO{" +
            "id=" + getId() +
            ", idcaution=" + getIdcaution() +
            ", montantCaution=" + getMontantCaution() +
            ", objet='" + getObjet() + "'" +
            ", numeroCaution='" + getNumeroCaution() + "'" +
            ", dateDemande='" + getDateDemande() + "'" +
            ", dateRetrait='" + getDateRetrait() + "'" +
            ", dateDepot='" + getDateDepot() + "'" +
            ", numeroMarche='" + getNumeroMarche() + "'" +
            ", statusCaution='" + getStatusCaution() + "'" +
            ", typeCaution='" + getTypeCaution() + "'" +
            "}";
    }
}
