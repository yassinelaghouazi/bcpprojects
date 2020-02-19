package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.CautionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.Caution}.
 */
public interface CautionService {

    /**
     * Save a caution.
     *
     * @param cautionDTO the entity to save.
     * @return the persisted entity.
     */
    CautionDTO save(CautionDTO cautionDTO);

    /**
     * Get all the cautions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CautionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" caution.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CautionDTO> findOne(Long id);

    /**
     * Delete the "id" caution.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
