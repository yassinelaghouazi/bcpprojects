package ma.jconsulting.applications.btpproject.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ma.jconsulting.applications.btpproject.domain.Company} entity.
 */
public class CompanyDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idcompany;

    private String companyName;

    @Lob
    private byte[] companyLogo;

    private String companyLogoContentType;

    private Long bopportunityId;

    private Long projectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(Long idcompany) {
        this.idcompany = idcompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLogoContentType() {
        return companyLogoContentType;
    }

    public void setCompanyLogoContentType(String companyLogoContentType) {
        this.companyLogoContentType = companyLogoContentType;
    }

    public Long getBopportunityId() {
        return bopportunityId;
    }

    public void setBopportunityId(Long bopportunityId) {
        this.bopportunityId = bopportunityId;
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

        CompanyDTO companyDTO = (CompanyDTO) o;
        if (companyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + getId() +
            ", idcompany=" + getIdcompany() +
            ", companyName='" + getCompanyName() + "'" +
            ", companyLogo='" + getCompanyLogo() + "'" +
            ", bopportunityId=" + getBopportunityId() +
            ", projectId=" + getProjectId() +
            "}";
    }
}
