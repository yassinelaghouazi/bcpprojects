package ma.jconsulting.applications.btpproject.repository;

import ma.jconsulting.applications.btpproject.domain.OrdersProducts;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OrdersProducts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdersProductsRepository extends JpaRepository<OrdersProducts, Long> {

}
