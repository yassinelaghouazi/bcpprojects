package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.ProviderService;
import ma.jconsulting.applications.btpproject.domain.Provider;
import ma.jconsulting.applications.btpproject.repository.ProviderRepository;
import ma.jconsulting.applications.btpproject.service.dto.ProviderDTO;
import ma.jconsulting.applications.btpproject.service.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Provider}.
 */
@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {

    private final Logger log = LoggerFactory.getLogger(ProviderServiceImpl.class);

    private final ProviderRepository providerRepository;

    private final ProviderMapper providerMapper;

    public ProviderServiceImpl(ProviderRepository providerRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    /**
     * Save a provider.
     *
     * @param providerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProviderDTO save(ProviderDTO providerDTO) {
        log.debug("Request to save Provider : {}", providerDTO);
        Provider provider = providerMapper.toEntity(providerDTO);
        provider = providerRepository.save(provider);
        return providerMapper.toDto(provider);
    }

    /**
     * Get all the providers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProviderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Providers");
        return providerRepository.findAll(pageable)
            .map(providerMapper::toDto);
    }

    /**
     * Get one provider by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProviderDTO> findOne(Long id) {
        log.debug("Request to get Provider : {}", id);
        return providerRepository.findById(id)
            .map(providerMapper::toDto);
    }

    /**
     * Delete the provider by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Provider : {}", id);
        providerRepository.deleteById(id);
    }
}
