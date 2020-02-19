package ma.jconsulting.applications.btpproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_project", nullable = false)
    private Long idProject;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "budget")
    private Double budget;

    @Column(name = "numero_marche")
    private String numeroMarche;

    @OneToMany(mappedBy = "project")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Company> companies = new HashSet<>();

    @OneToMany(mappedBy = "project")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bopportunity> bopportuniys = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("projects")
    private Caution caution;

    @ManyToOne
    @JsonIgnoreProperties("projects")
    private Delivery delivery;

    @ManyToOne
    @JsonIgnoreProperties("projects")
    private Orders orders;

    @ManyToOne
    @JsonIgnoreProperties("projects")
    private Reglement reglement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProject() {
        return idProject;
    }

    public Project idProject(Long idProject) {
        this.idProject = idProject;
        return this;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public Project projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Project projectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
        return this;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Double getBudget() {
        return budget;
    }

    public Project budget(Double budget) {
        this.budget = budget;
        return this;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getNumeroMarche() {
        return numeroMarche;
    }

    public Project numeroMarche(String numeroMarche) {
        this.numeroMarche = numeroMarche;
        return this;
    }

    public void setNumeroMarche(String numeroMarche) {
        this.numeroMarche = numeroMarche;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public Project companies(Set<Company> companies) {
        this.companies = companies;
        return this;
    }

    public Project addCompany(Company company) {
        this.companies.add(company);
        company.setProject(this);
        return this;
    }

    public Project removeCompany(Company company) {
        this.companies.remove(company);
        company.setProject(null);
        return this;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Set<Bopportunity> getBopportuniys() {
        return bopportuniys;
    }

    public Project bopportuniys(Set<Bopportunity> bopportunities) {
        this.bopportuniys = bopportunities;
        return this;
    }

    public Project addBopportuniy(Bopportunity bopportunity) {
        this.bopportuniys.add(bopportunity);
        bopportunity.setProject(this);
        return this;
    }

    public Project removeBopportuniy(Bopportunity bopportunity) {
        this.bopportuniys.remove(bopportunity);
        bopportunity.setProject(null);
        return this;
    }

    public void setBopportuniys(Set<Bopportunity> bopportunities) {
        this.bopportuniys = bopportunities;
    }

    public Caution getCaution() {
        return caution;
    }

    public Project caution(Caution caution) {
        this.caution = caution;
        return this;
    }

    public void setCaution(Caution caution) {
        this.caution = caution;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Project delivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Orders getOrders() {
        return orders;
    }

    public Project orders(Orders orders) {
        this.orders = orders;
        return this;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Reglement getReglement() {
        return reglement;
    }

    public Project reglement(Reglement reglement) {
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
        if (!(o instanceof Project)) {
            return false;
        }
        return id != null && id.equals(((Project) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", idProject=" + getIdProject() +
            ", projectName='" + getProjectName() + "'" +
            ", projectDescription='" + getProjectDescription() + "'" +
            ", budget=" + getBudget() +
            ", numeroMarche='" + getNumeroMarche() + "'" +
            "}";
    }
}
