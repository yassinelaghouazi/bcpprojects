package ma.jconsulting.applications.btpproject.repository;

import ma.jconsulting.applications.btpproject.domain.Provider;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Provider entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
