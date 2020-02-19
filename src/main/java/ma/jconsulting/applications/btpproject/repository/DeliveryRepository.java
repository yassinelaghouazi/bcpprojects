package ma.jconsulting.applications.btpproject.repository;

import ma.jconsulting.applications.btpproject.domain.Delivery;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Delivery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
