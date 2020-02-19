package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.BopportunityService;
import ma.jconsulting.applications.btpproject.domain.Bopportunity;
import ma.jconsulting.applications.btpproject.repository.BopportunityRepository;
import ma.jconsulting.applications.btpproject.service.dto.BopportunityDTO;
import ma.jconsulting.applications.btpproject.service.mapper.BopportunityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bopportunity}.
 */
@Service
@Transactional
public class BopportunityServiceImpl implements BopportunityService {

    private final Logger log = LoggerFactory.getLogger(BopportunityServiceImpl.class);

    private final BopportunityRepository bopportunityRepository;

    private final BopportunityMapper bopportunityMapper;

    public BopportunityServiceImpl(BopportunityRepository bopportunityRepository, BopportunityMapper bopportunityMapper) {
        this.bopportunityRepository = bopportunityRepository;
        this.bopportunityMapper = bopportunityMapper;
    }

    /**
     * Save a bopportunity.
     *
     * @param bopportunityDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BopportunityDTO save(BopportunityDTO bopportunityDTO) {
        log.debug("Request to save Bopportunity : {}", bopportunityDTO);
        Bopportunity bopportunity = bopportunityMapper.toEntity(bopportunityDTO);
        bopportunity = bopportunityRepository.save(bopportunity);
        return bopportunityMapper.toDto(bopportunity);
    }

    /**
     * Get all the bopportunities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BopportunityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bopportunities");
        return bopportunityRepository.findAll(pageable)
            .map(bopportunityMapper::toDto);
    }

    /**
     * Get one bopportunity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BopportunityDTO> findOne(Long id) {
        log.debug("Request to get Bopportunity : {}", id);
        return bopportunityRepository.findById(id)
            .map(bopportunityMapper::toDto);
    }

    /**
     * Delete the bopportunity by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bopportunity : {}", id);
        bopportunityRepository.deleteById(id);
    }
}
