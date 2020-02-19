package ma.jconsulting.applications.btpproject.repository;

import ma.jconsulting.applications.btpproject.domain.Products;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Products entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

}
