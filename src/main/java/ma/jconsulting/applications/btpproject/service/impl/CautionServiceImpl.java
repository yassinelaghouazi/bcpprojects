package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.CautionService;
import ma.jconsulting.applications.btpproject.domain.Caution;
import ma.jconsulting.applications.btpproject.repository.CautionRepository;
import ma.jconsulting.applications.btpproject.service.dto.CautionDTO;
import ma.jconsulting.applications.btpproject.service.mapper.CautionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Caution}.
 */
@Service
@Transactional
public class CautionServiceImpl implements CautionService {

    private final Logger log = LoggerFactory.getLogger(CautionServiceImpl.class);

    private final CautionRepository cautionRepository;

    private final CautionMapper cautionMapper;

    public CautionServiceImpl(CautionRepository cautionRepository, CautionMapper cautionMapper) {
        this.cautionRepository = cautionRepository;
        this.cautionMapper = cautionMapper;
    }

    /**
     * Save a caution.
     *
     * @param cautionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CautionDTO save(CautionDTO cautionDTO) {
        log.debug("Request to save Caution : {}", cautionDTO);
        Caution caution = cautionMapper.toEntity(cautionDTO);
        caution = cautionRepository.save(caution);
        return cautionMapper.toDto(caution);
    }

    /**
     * Get all the cautions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CautionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cautions");
        return cautionRepository.findAll(pageable)
            .map(cautionMapper::toDto);
    }

    /**
     * Get one caution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CautionDTO> findOne(Long id) {
        log.debug("Request to get Caution : {}", id);
        return cautionRepository.findById(id)
            .map(cautionMapper::toDto);
    }

    /**
     * Delete the caution by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Caution : {}", id);
        cautionRepository.deleteById(id);
    }
}
