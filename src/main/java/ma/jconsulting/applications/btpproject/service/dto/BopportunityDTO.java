package ma.jconsulting.applications.btpproject.service.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Bopportunity} entity.
 */
public class BopportunityDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idBopportunity;

    private LocalDate dateRemisePlis;

    private Double montantCaution;

    private String objetAffaire;

    private Double estimationBudget;

    private String commentaire;

    @NotNull
    private String numeroAppelOffre;

    private String numeroMarche;

    private String url;

    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")

    private Long cautionId;

    private Long projectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBopportunity() {
        return idBopportunity;
    }

    public void setIdBopportunity(Long idBopportunity) {
        this.idBopportunity = idBopportunity;
    }

    public LocalDate getDateRemisePlis() {
        return dateRemisePlis;
    }

    public void setDateRemisePlis(LocalDate dateRemisePlis) {
        this.dateRemisePlis = dateRemisePlis;
    }

    public Double getMontantCaution() {
        return montantCaution;
    }

    public void setMontantCaution(Double montantCaution) {
        this.montantCaution = montantCaution;
    }

    public String getObjetAffaire() {
        return objetAffaire;
    }

    public void setObjetAffaire(String objetAffaire) {
        this.objetAffaire = objetAffaire;
    }

    public Double getEstimationBudget() {
        return estimationBudget;
    }

    public void setEstimationBudget(Double estimationBudget) {
        this.estimationBudget = estimationBudget;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNumeroAppelOffre() {
        return numeroAppelOffre;
    }

    public void setNumeroAppelOffre(String numeroAppelOffre) {
        this.numeroAppelOffre = numeroAppelOffre;
    }

    public String getNumeroMarche() {
        return numeroMarche;
    }

    public void setNumeroMarche(String numeroMarche) {
        this.numeroMarche = numeroMarche;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCautionId() {
        return cautionId;
    }

    public void setCautionId(Long cautionId) {
        this.cautionId = cautionId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BopportunityDTO bopportunityDTO = (BopportunityDTO) o;
        if (bopportunityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bopportunityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BopportunityDTO{" +
            "id=" + getId() +
            ", idBopportunity=" + getIdBopportunity() +
            ", dateRemisePlis='" + getDateRemisePlis() + "'" +
            ", montantCaution=" + getMontantCaution() +
            ", objetAffaire='" + getObjetAffaire() + "'" +
            ", estimationBudget=" + getEstimationBudget() +
            ", commentaire='" + getCommentaire() + "'" +
            ", numeroAppelOffre='" + getNumeroAppelOffre() + "'" +
            ", numeroMarche='" + getNumeroMarche() + "'" +
            ", url='" + getUrl() + "'" +
            ", cautionId=" + getCautionId() +
            ", projectId=" + getProjectId() +
            "}";
    }
}
