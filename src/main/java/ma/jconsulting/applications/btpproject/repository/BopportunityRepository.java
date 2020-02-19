package ma.jconsulting.applications.btpproject.repository;

import ma.jconsulting.applications.btpproject.domain.Bopportunity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bopportunity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BopportunityRepository extends JpaRepository<Bopportunity, Long> {

}
