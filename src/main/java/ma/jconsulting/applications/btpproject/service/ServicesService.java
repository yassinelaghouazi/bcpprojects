package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.ServicesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.Services}.
 */
public interface ServicesService {

    /**
     * Save a services.
     *
     * @param servicesDTO the entity to save.
     * @return the persisted entity.
     */
    ServicesDTO save(ServicesDTO servicesDTO);

    /**
     * Get all the services.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServicesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" services.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServicesDTO> findOne(Long id);

    /**
     * Delete the "id" services.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
