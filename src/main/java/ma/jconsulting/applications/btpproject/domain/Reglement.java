package ma.jconsulting.applications.btpproject.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Reglement.
 */
@Entity
@Table(name = "reglement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reglement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_reglement", nullable = false)
    private Long idReglement;

    @Column(name = "date_effet_reglement")
    private LocalDate dateEffetReglement;

    @Column(name = "date_reglement")
    private LocalDate dateReglement;

    @Column(name = "montant_reglement")
    private Double montantReglement;

    @Column(name = "commentaire")
    private String commentaire;

    @OneToMany(mappedBy = "reglement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Provider> providers = new HashSet<>();

    @OneToMany(mappedBy = "reglement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "reglement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Orders> orders = new HashSet<>();

    @OneToMany(mappedBy = "reglement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Delivery> deliveries = new HashSet<>();

    @OneToMany(mappedBy = "reglement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ModePaiement> modePaiements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdReglement() {
        return idReglement;
    }

    public Reglement idReglement(Long idReglement) {
        this.idReglement = idReglement;
        return this;
    }

    public void setIdReglement(Long idReglement) {
        this.idReglement = idReglement;
    }

    public LocalDate getDateEffetReglement() {
        return dateEffetReglement;
    }

    public Reglement dateEffetReglement(LocalDate dateEffetReglement) {
        this.dateEffetReglement = dateEffetReglement;
        return this;
    }

    public void setDateEffetReglement(LocalDate dateEffetReglement) {
        this.dateEffetReglement = dateEffetReglement;
    }

    public LocalDate getDateReglement() {
        return dateReglement;
    }

    public Reglement dateReglement(LocalDate dateReglement) {
        this.dateReglement = dateReglement;
        return this;
    }

    public void setDateReglement(LocalDate dateReglement) {
        this.dateReglement = dateReglement;
    }

    public Double getMontantReglement() {
        return montantReglement;
    }

    public Reglement montantReglement(Double montantReglement) {
        this.montantReglement = montantReglement;
        return this;
    }

    public void setMontantReglement(Double montantReglement) {
        this.montantReglement = montantReglement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Reglement commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Set<Provider> getProviders() {
        return providers;
    }

    public Reglement providers(Set<Provider> providers) {
        this.providers = providers;
        return this;
    }

    public Reglement addProvider(Provider provider) {
        this.providers.add(provider);
        provider.setReglement(this);
        return this;
    }

    public Reglement removeProvider(Provider provider) {
        this.providers.remove(provider);
        provider.setReglement(null);
        return this;
    }

    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Reglement projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Reglement addProject(Project project) {
        this.projects.add(project);
        project.setReglement(this);
        return this;
    }

    public Reglement removeProject(Project project) {
        this.projects.remove(project);
        project.setReglement(null);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public Reglement orders(Set<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public Reglement addOrders(Orders orders) {
        this.orders.add(orders);
        orders.setReglement(this);
        return this;
    }

    public Reglement removeOrders(Orders orders) {
        this.orders.remove(orders);
        orders.setReglement(null);
        return this;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<Delivery> getDeliveries() {
        return deliveries;
    }

    public Reglement deliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
        return this;
    }

    public Reglement addDelivery(Delivery delivery) {
        this.deliveries.add(delivery);
        delivery.setReglement(this);
        return this;
    }

    public Reglement removeDelivery(Delivery delivery) {
        this.deliveries.remove(delivery);
        delivery.setReglement(null);
        return this;
    }

    public void setDeliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public Set<ModePaiement> getModePaiements() {
        return modePaiements;
    }

    public Reglement modePaiements(Set<ModePaiement> modePaiements) {
        this.modePaiements = modePaiements;
        return this;
    }

    public Reglement addModePaiement(ModePaiement modePaiement) {
        this.modePaiements.add(modePaiement);
        modePaiement.setReglement(this);
        return this;
    }

    public Reglement removeModePaiement(ModePaiement modePaiement) {
        this.modePaiements.remove(modePaiement);
        modePaiement.setReglement(null);
        return this;
    }

    public void setModePaiements(Set<ModePaiement> modePaiements) {
        this.modePaiements = modePaiements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reglement)) {
            return false;
        }
        return id != null && id.equals(((Reglement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Reglement{" +
            "id=" + getId() +
            ", idReglement=" + getIdReglement() +
            ", dateEffetReglement='" + getDateEffetReglement() + "'" +
            ", dateReglement='" + getDateReglement() + "'" +
            ", montantReglement=" + getMontantReglement() +
            ", commentaire='" + getCommentaire() + "'" +
            "}";
    }
}
