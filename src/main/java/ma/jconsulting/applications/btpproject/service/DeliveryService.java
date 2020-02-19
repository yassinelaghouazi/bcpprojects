package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.DeliveryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.Delivery}.
 */
public interface DeliveryService {

    /**
     * Save a delivery.
     *
     * @param deliveryDTO the entity to save.
     * @return the persisted entity.
     */
    DeliveryDTO save(DeliveryDTO deliveryDTO);

    /**
     * Get all the deliveries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeliveryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" delivery.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliveryDTO> findOne(Long id);

    /**
     * Delete the "id" delivery.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
