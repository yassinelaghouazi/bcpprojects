package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.ModePaiementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.ModePaiement}.
 */
public interface ModePaiementService {

    /**
     * Save a modePaiement.
     *
     * @param modePaiementDTO the entity to save.
     * @return the persisted entity.
     */
    ModePaiementDTO save(ModePaiementDTO modePaiementDTO);

    /**
     * Get all the modePaiements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ModePaiementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" modePaiement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ModePaiementDTO> findOne(Long id);

    /**
     * Delete the "id" modePaiement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
