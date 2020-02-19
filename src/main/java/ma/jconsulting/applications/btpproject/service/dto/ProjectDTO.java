package ma.jconsulting.applications.btpproject.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Project} entity.
 */
public class ProjectDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idProject;

    private String projectName;

    private String projectDescription;

    private Double budget;

    private String numeroMarche;


    private Long cautionId;

    private Long deliveryId;

    private Long ordersId;

    private Long reglementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getNumeroMarche() {
        return numeroMarche;
    }

    public void setNumeroMarche(String numeroMarche) {
        this.numeroMarche = numeroMarche;
    }

    public Long getCautionId() {
        return cautionId;
    }

    public void setCautionId(Long cautionId) {
        this.cautionId = cautionId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
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

        ProjectDTO projectDTO = (ProjectDTO) o;
        if (projectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
            "id=" + getId() +
            ", idProject=" + getIdProject() +
            ", projectName='" + getProjectName() + "'" +
            ", projectDescription='" + getProjectDescription() + "'" +
            ", budget=" + getBudget() +
            ", numeroMarche='" + getNumeroMarche() + "'" +
            ", cautionId=" + getCautionId() +
            ", deliveryId=" + getDeliveryId() +
            ", ordersId=" + getOrdersId() +
            ", reglementId=" + getReglementId() +
            "}";
    }
}
