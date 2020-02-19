package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.MaitreOuvrageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.MaitreOuvrage}.
 */
public interface MaitreOuvrageService {

    /**
     * Save a maitreOuvrage.
     *
     * @param maitreOuvrageDTO the entity to save.
     * @return the persisted entity.
     */
    MaitreOuvrageDTO save(MaitreOuvrageDTO maitreOuvrageDTO);

    /**
     * Get all the maitreOuvrages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MaitreOuvrageDTO> findAll(Pageable pageable);

    /**
     * Get the "id" maitreOuvrage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MaitreOuvrageDTO> findOne(Long id);

    /**
     * Delete the "id" maitreOuvrage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
