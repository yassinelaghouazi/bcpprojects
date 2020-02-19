package ma.jconsulting.applications.btpproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Bopportunity.
 */
@Entity
@Table(name = "bopportunity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bopportunity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_bopportunity", nullable = false)
    private Long idBopportunity;

    @Column(name = "date_remise_plis")
    private LocalDate dateRemisePlis;

    @Column(name = "montant_caution")
    private Double montantCaution;

    @Column(name = "objet_affaire")
    private String objetAffaire;

    @Column(name = "estimation_budget")
    private Double estimationBudget;

    @Column(name = "commentaire")
    private String commentaire;

    @NotNull
    @Column(name = "numero_appel_offre", nullable = false)
    private String numeroAppelOffre;

    @Column(name = "numero_marche")
    private String numeroMarche;

    @Column(name = "url")
    private String url;

    @OneToMany(mappedBy = "bopportunity")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MaitreOuvrage> maitreOuvrages = new HashSet<>();

    /**
     * A relationship
     */
    @OneToMany(mappedBy = "bopportunity")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Company> companies = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("bopportuniys")
    private Caution caution;

    @ManyToOne
    @JsonIgnoreProperties("bopportuniys")
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBopportunity() {
        return idBopportunity;
    }

    public Bopportunity idBopportunity(Long idBopportunity) {
        this.idBopportunity = idBopportunity;
        return this;
    }

    public void setIdBopportunity(Long idBopportunity) {
        this.idBopportunity = idBopportunity;
    }

    public LocalDate getDateRemisePlis() {
        return dateRemisePlis;
    }

    public Bopportunity dateRemisePlis(LocalDate dateRemisePlis) {
        this.dateRemisePlis = dateRemisePlis;
        return this;
    }

    public void setDateRemisePlis(LocalDate dateRemisePlis) {
        this.dateRemisePlis = dateRemisePlis;
    }

    public Double getMontantCaution() {
        return montantCaution;
    }

    public Bopportunity montantCaution(Double montantCaution) {
        this.montantCaution = montantCaution;
        return this;
    }

    public void setMontantCaution(Double montantCaution) {
        this.montantCaution = montantCaution;
    }

    public String getObjetAffaire() {
        return objetAffaire;
    }

    public Bopportunity objetAffaire(String objetAffaire) {
        this.objetAffaire = objetAffaire;
        return this;
    }

    public void setObjetAffaire(String objetAffaire) {
        this.objetAffaire = objetAffaire;
    }

    public Double getEstimationBudget() {
        return estimationBudget;
    }

    public Bopportunity estimationBudget(Double estimationBudget) {
        this.estimationBudget = estimationBudget;
        return this;
    }

    public void setEstimationBudget(Double estimationBudget) {
        this.estimationBudget = estimationBudget;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Bopportunity commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNumeroAppelOffre() {
        return numeroAppelOffre;
    }

    public Bopportunity numeroAppelOffre(String numeroAppelOffre) {
        this.numeroAppelOffre = numeroAppelOffre;
        return this;
    }

    public void setNumeroAppelOffre(String numeroAppelOffre) {
        this.numeroAppelOffre = numeroAppelOffre;
    }

    public String getNumeroMarche() {
        return numeroMarche;
    }

    public Bopportunity numeroMarche(String numeroMarche) {
        this.numeroMarche = numeroMarche;
        return this;
    }

    public void setNumeroMarche(String numeroMarche) {
        this.numeroMarche = numeroMarche;
    }

    public String getUrl() {
        return url;
    }

    public Bopportunity url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<MaitreOuvrage> getMaitreOuvrages() {
        return maitreOuvrages;
    }

    public Bopportunity maitreOuvrages(Set<MaitreOuvrage> maitreOuvrages) {
        this.maitreOuvrages = maitreOuvrages;
        return this;
    }

    public Bopportunity addMaitreOuvrage(MaitreOuvrage maitreOuvrage) {
        this.maitreOuvrages.add(maitreOuvrage);
        maitreOuvrage.setBopportunity(this);
        return this;
    }

    public Bopportunity removeMaitreOuvrage(MaitreOuvrage maitreOuvrage) {
        this.maitreOuvrages.remove(maitreOuvrage);
        maitreOuvrage.setBopportunity(null);
        return this;
    }

    public void setMaitreOuvrages(Set<MaitreOuvrage> maitreOuvrages) {
        this.maitreOuvrages = maitreOuvrages;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public Bopportunity companies(Set<Company> companies) {
        this.companies = companies;
        return this;
    }

    public Bopportunity addCompany(Company company) {
        this.companies.add(company);
        company.setBopportunity(this);
        return this;
    }

    public Bopportunity removeCompany(Company company) {
        this.companies.remove(company);
        company.setBopportunity(null);
        return this;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Caution getCaution() {
        return caution;
    }

    public Bopportunity caution(Caution caution) {
        this.caution = caution;
        return this;
    }

    public void setCaution(Caution caution) {
        this.caution = caution;
    }

    public Project getProject() {
        return project;
    }

    public Bopportunity project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bopportunity)) {
            return false;
        }
        return id != null && id.equals(((Bopportunity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Bopportunity{" +
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
            "}";
    }
}
