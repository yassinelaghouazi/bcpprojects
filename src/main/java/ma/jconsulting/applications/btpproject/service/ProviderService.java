package ma.jconsulting.applications.btpproject.service;

import ma.jconsulting.applications.btpproject.service.dto.ProviderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ma.jconsulting.applications.btpproject.domain.Provider}.
 */
public interface ProviderService {

    /**
     * Save a provider.
     *
     * @param providerDTO the entity to save.
     * @return the persisted entity.
     */
    ProviderDTO save(ProviderDTO providerDTO);

    /**
     * Get all the providers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProviderDTO> findAll(Pageable pageable);

    /**
     * Get the "id" provider.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProviderDTO> findOne(Long id);

    /**
     * Delete the "id" provider.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
