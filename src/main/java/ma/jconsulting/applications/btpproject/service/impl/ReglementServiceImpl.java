package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.ReglementService;
import ma.jconsulting.applications.btpproject.domain.Reglement;
import ma.jconsulting.applications.btpproject.repository.ReglementRepository;
import ma.jconsulting.applications.btpproject.service.dto.ReglementDTO;
import ma.jconsulting.applications.btpproject.service.mapper.ReglementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Reglement}.
 */
@Service
@Transactional
public class ReglementServiceImpl implements ReglementService {

    private final Logger log = LoggerFactory.getLogger(ReglementServiceImpl.class);

    private final ReglementRepository reglementRepository;

    private final ReglementMapper reglementMapper;

    public ReglementServiceImpl(ReglementRepository reglementRepository, ReglementMapper reglementMapper) {
        this.reglementRepository = reglementRepository;
        this.reglementMapper = reglementMapper;
    }

    /**
     * Save a reglement.
     *
     * @param reglementDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReglementDTO save(ReglementDTO reglementDTO) {
        log.debug("Request to save Reglement : {}", reglementDTO);
        Reglement reglement = reglementMapper.toEntity(reglementDTO);
        reglement = reglementRepository.save(reglement);
        return reglementMapper.toDto(reglement);
    }

    /**
     * Get all the reglements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReglementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reglements");
        return reglementRepository.findAll(pageable)
            .map(reglementMapper::toDto);
    }

    /**
     * Get one reglement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReglementDTO> findOne(Long id) {
        log.debug("Request to get Reglement : {}", id);
        return reglementRepository.findById(id)
            .map(reglementMapper::toDto);
    }

    /**
     * Delete the reglement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reglement : {}", id);
        reglementRepository.deleteById(id);
    }
}
