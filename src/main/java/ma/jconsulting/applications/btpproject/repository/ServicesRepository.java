package ma.jconsulting.applications.btpproject.repository;

import ma.jconsulting.applications.btpproject.domain.Services;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Services entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

}
