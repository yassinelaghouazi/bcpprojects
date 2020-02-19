package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.OrdersProductsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.OrdersProducts}.
 */
public interface OrdersProductsService {

    /**
     * Save a ordersProducts.
     *
     * @param ordersProductsDTO the entity to save.
     * @return the persisted entity.
     */
    OrdersProductsDTO save(OrdersProductsDTO ordersProductsDTO);

    /**
     * Get all the ordersProducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrdersProductsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ordersProducts.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrdersProductsDTO> findOne(Long id);

    /**
     * Delete the "id" ordersProducts.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
