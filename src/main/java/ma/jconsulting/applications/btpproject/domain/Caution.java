package ma.jconsulting.applications.btpproject.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import ma.jconsulting.applications.btpproject.domain.enumeration.StatusCaution;

import ma.jconsulting.applications.btpproject.domain.enumeration.TypeCaution;

/**
 * A Caution.
 */
@Entity
@Table(name = "caution")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Caution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "idcaution", nullable = false)
    private Long idcaution;

    @Column(name = "montant_caution")
    private Double montantCaution;

    @Column(name = "objet")
    private String objet;

    @Column(name = "numero_caution")
    private String numeroCaution;

    @Column(name = "date_demande")
    private LocalDate dateDemande;

    @Column(name = "date_retrait")
    private LocalDate dateRetrait;

    @Column(name = "date_depot")
    private LocalDate dateDepot;

    @Column(name = "numero_marche")
    private String numeroMarche;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_caution")
    private StatusCaution statusCaution;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_caution")
    private TypeCaution typeCaution;

    @OneToMany(mappedBy = "caution")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bopportunity> bopportuniys = new HashSet<>();

    /**
     * A relationship
     */
    @OneToMany(mappedBy = "caution")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MaitreOuvrage> maitreouvrages = new HashSet<>();

    @OneToMany(mappedBy = "caution")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Banque> banques = new HashSet<>();

    @OneToMany(mappedBy = "caution")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdcaution() {
        return idcaution;
    }

    public Caution idcaution(Long idcaution) {
        this.idcaution = idcaution;
        return this;
    }

    public void setIdcaution(Long idcaution) {
        this.idcaution = idcaution;
    }

    public Double getMontantCaution() {
        return montantCaution;
    }

    public Caution montantCaution(Double montantCaution) {
        this.montantCaution = montantCaution;
        return this;
    }

    public void setMontantCaution(Double montantCaution) {
        this.montantCaution = montantCaution;
    }

    public String getObjet() {
        return objet;
    }

    public Caution objet(String objet) {
        this.objet = objet;
        return this;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getNumeroCaution() {
        return numeroCaution;
    }

    public Caution numeroCaution(String numeroCaution) {
        this.numeroCaution = numeroCaution;
        return this;
    }

    public void setNumeroCaution(String numeroCaution) {
        this.numeroCaution = numeroCaution;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public Caution dateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
        return this;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalDate getDateRetrait() {
        return dateRetrait;
    }

    public Caution dateRetrait(LocalDate dateRetrait) {
        this.dateRetrait = dateRetrait;
        return this;
    }

    public void setDateRetrait(LocalDate dateRetrait) {
        this.dateRetrait = dateRetrait;
    }

    public LocalDate getDateDepot() {
        return dateDepot;
    }

    public Caution dateDepot(LocalDate dateDepot) {
        this.dateDepot = dateDepot;
        return this;
    }

    public void setDateDepot(LocalDate dateDepot) {
        this.dateDepot = dateDepot;
    }

    public String getNumeroMarche() {
        return numeroMarche;
    }

    public Caution numeroMarche(String numeroMarche) {
        this.numeroMarche = numeroMarche;
        return this;
    }

    public void setNumeroMarche(String numeroMarche) {
        this.numeroMarche = numeroMarche;
    }

    public StatusCaution getStatusCaution() {
        return statusCaution;
    }

    public Caution statusCaution(StatusCaution statusCaution) {
        this.statusCaution = statusCaution;
        return this;
    }

    public void setStatusCaution(StatusCaution statusCaution) {
        this.statusCaution = statusCaution;
    }

    public TypeCaution getTypeCaution() {
        return typeCaution;
    }

    public Caution typeCaution(TypeCaution typeCaution) {
        this.typeCaution = typeCaution;
        return this;
    }

    public void setTypeCaution(TypeCaution typeCaution) {
        this.typeCaution = typeCaution;
    }

    public Set<Bopportunity> getBopportuniys() {
        return bopportuniys;
    }

    public Caution bopportuniys(Set<Bopportunity> bopportunities) {
        this.bopportuniys = bopportunities;
        return this;
    }

    public Caution addBopportuniy(Bopportunity bopportunity) {
        this.bopportuniys.add(bopportunity);
        bopportunity.setCaution(this);
        return this;
    }

    public Caution removeBopportuniy(Bopportunity bopportunity) {
        this.bopportuniys.remove(bopportunity);
        bopportunity.setCaution(null);
        return this;
    }

    public void setBopportuniys(Set<Bopportunity> bopportunities) {
        this.bopportuniys = bopportunities;
    }

    public Set<MaitreOuvrage> getMaitreouvrages() {
        return maitreouvrages;
    }

    public Caution maitreouvrages(Set<MaitreOuvrage> maitreOuvrages) {
        this.maitreouvrages = maitreOuvrages;
        return this;
    }

    public Caution addMaitreouvrage(MaitreOuvrage maitreOuvrage) {
        this.maitreouvrages.add(maitreOuvrage);
        maitreOuvrage.setCaution(this);
        return this;
    }

    public Caution removeMaitreouvrage(MaitreOuvrage maitreOuvrage) {
        this.maitreouvrages.remove(maitreOuvrage);
        maitreOuvrage.setCaution(null);
        return this;
    }

    public void setMaitreouvrages(Set<MaitreOuvrage> maitreOuvrages) {
        this.maitreouvrages = maitreOuvrages;
    }

    public Set<Banque> getBanques() {
        return banques;
    }

    public Caution banques(Set<Banque> banques) {
        this.banques = banques;
        return this;
    }

    public Caution addBanque(Banque banque) {
        this.banques.add(banque);
        banque.setCaution(this);
        return this;
    }

    public Caution removeBanque(Banque banque) {
        this.banques.remove(banque);
        banque.setCaution(null);
        return this;
    }

    public void setBanques(Set<Banque> banques) {
        this.banques = banques;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Caution projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Caution addProject(Project project) {
        this.projects.add(project);
        project.setCaution(this);
        return this;
    }

    public Caution removeProject(Project project) {
        this.projects.remove(project);
        project.setCaution(null);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Caution)) {
            return false;
        }
        return id != null && id.equals(((Caution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Caution{" +
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
