package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.BopportunityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.Bopportunity}.
 */
public interface BopportunityService {

    /**
     * Save a bopportunity.
     *
     * @param bopportunityDTO the entity to save.
     * @return the persisted entity.
     */
    BopportunityDTO save(BopportunityDTO bopportunityDTO);

    /**
     * Get all the bopportunities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BopportunityDTO> findAll(Pageable pageable);

    /**
     * Get the "id" bopportunity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BopportunityDTO> findOne(Long id);

    /**
     * Delete the "id" bopportunity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
