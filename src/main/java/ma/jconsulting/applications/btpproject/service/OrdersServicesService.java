package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.OrdersServicesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.OrdersServices}.
 */
public interface OrdersServicesService {

    /**
     * Save a ordersServices.
     *
     * @param ordersServicesDTO the entity to save.
     * @return the persisted entity.
     */
    OrdersServicesDTO save(OrdersServicesDTO ordersServicesDTO);

    /**
     * Get all the ordersServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrdersServicesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ordersServices.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrdersServicesDTO> findOne(Long id);

    /**
     * Delete the "id" ordersServices.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
