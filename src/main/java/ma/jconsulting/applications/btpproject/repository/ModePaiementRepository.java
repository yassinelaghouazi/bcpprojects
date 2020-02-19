package ma.jconsulting.applications.btpproject.repository;

import ma.jconsulting.applications.btpproject.domain.ModePaiement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ModePaiement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModePaiementRepository extends JpaRepository<ModePaiement, Long> {

}
