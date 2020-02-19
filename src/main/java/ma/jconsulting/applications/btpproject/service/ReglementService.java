package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.ReglementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.Reglement}.
 */
public interface ReglementService {

    /**
     * Save a reglement.
     *
     * @param reglementDTO the entity to save.
     * @return the persisted entity.
     */
    ReglementDTO save(ReglementDTO reglementDTO);

    /**
     * Get all the reglements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReglementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" reglement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReglementDTO> findOne(Long id);

    /**
     * Delete the "id" reglement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
