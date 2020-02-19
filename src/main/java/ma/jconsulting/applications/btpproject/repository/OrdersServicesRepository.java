package ma.jconsulting.applications.btpproject.repository;

import ma.jconsulting.applications.btpproject.domain.OrdersServices;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OrdersServices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdersServicesRepository extends JpaRepository<OrdersServices, Long> {

}
