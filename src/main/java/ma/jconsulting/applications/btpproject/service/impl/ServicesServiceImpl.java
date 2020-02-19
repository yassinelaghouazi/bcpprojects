package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.ServicesService;
import ma.jconsulting.applications.btpproject.domain.Services;
import ma.jconsulting.applications.btpproject.repository.ServicesRepository;
import ma.jconsulting.applications.btpproject.service.dto.ServicesDTO;
import ma.jconsulting.applications.btpproject.service.mapper.ServicesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Services}.
 */
@Service
@Transactional
public class ServicesServiceImpl implements ServicesService {

    private final Logger log = LoggerFactory.getLogger(ServicesServiceImpl.class);

    private final ServicesRepository servicesRepository;

    private final ServicesMapper servicesMapper;

    public ServicesServiceImpl(ServicesRepository servicesRepository, ServicesMapper servicesMapper) {
        this.servicesRepository = servicesRepository;
        this.servicesMapper = servicesMapper;
    }

    /**
     * Save a services.
     *
     * @param servicesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ServicesDTO save(ServicesDTO servicesDTO) {
        log.debug("Request to save Services : {}", servicesDTO);
        Services services = servicesMapper.toEntity(servicesDTO);
        services = servicesRepository.save(services);
        return servicesMapper.toDto(services);
    }

    /**
     * Get all the services.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ServicesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Services");
        return servicesRepository.findAll(pageable)
            .map(servicesMapper::toDto);
    }

    /**
     * Get one services by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ServicesDTO> findOne(Long id) {
        log.debug("Request to get Services : {}", id);
        return servicesRepository.findById(id)
            .map(servicesMapper::toDto);
    }

    /**
     * Delete the services by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Services : {}", id);
        servicesRepository.deleteById(id);
    }
}
