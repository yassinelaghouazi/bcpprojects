package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.MaitreOuvrageService;
import ma.jconsulting.applications.btpproject.domain.MaitreOuvrage;
import ma.jconsulting.applications.btpproject.repository.MaitreOuvrageRepository;
import ma.jconsulting.applications.btpproject.service.dto.MaitreOuvrageDTO;
import ma.jconsulting.applications.btpproject.service.mapper.MaitreOuvrageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MaitreOuvrage}.
 */
@Service
@Transactional
public class MaitreOuvrageServiceImpl implements MaitreOuvrageService {

    private final Logger log = LoggerFactory.getLogger(MaitreOuvrageServiceImpl.class);

    private final MaitreOuvrageRepository maitreOuvrageRepository;

    private final MaitreOuvrageMapper maitreOuvrageMapper;

    public MaitreOuvrageServiceImpl(MaitreOuvrageRepository maitreOuvrageRepository, MaitreOuvrageMapper maitreOuvrageMapper) {
        this.maitreOuvrageRepository = maitreOuvrageRepository;
        this.maitreOuvrageMapper = maitreOuvrageMapper;
    }

    /**
     * Save a maitreOuvrage.
     *
     * @param maitreOuvrageDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MaitreOuvrageDTO save(MaitreOuvrageDTO maitreOuvrageDTO) {
        log.debug("Request to save MaitreOuvrage : {}", maitreOuvrageDTO);
        MaitreOuvrage maitreOuvrage = maitreOuvrageMapper.toEntity(maitreOuvrageDTO);
        maitreOuvrage = maitreOuvrageRepository.save(maitreOuvrage);
        return maitreOuvrageMapper.toDto(maitreOuvrage);
    }

    /**
     * Get all the maitreOuvrages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MaitreOuvrageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MaitreOuvrages");
        return maitreOuvrageRepository.findAll(pageable)
            .map(maitreOuvrageMapper::toDto);
    }

    /**
     * Get one maitreOuvrage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MaitreOuvrageDTO> findOne(Long id) {
        log.debug("Request to get MaitreOuvrage : {}", id);
        return maitreOuvrageRepository.findById(id)
            .map(maitreOuvrageMapper::toDto);
    }

    /**
     * Delete the maitreOuvrage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MaitreOuvrage : {}", id);
        maitreOuvrageRepository.deleteById(id);
    }
}
