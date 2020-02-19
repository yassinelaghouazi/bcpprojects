package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.ModePaiementService;
import ma.jconsulting.applications.btpproject.domain.ModePaiement;
import ma.jconsulting.applications.btpproject.repository.ModePaiementRepository;
import ma.jconsulting.applications.btpproject.service.dto.ModePaiementDTO;
import ma.jconsulting.applications.btpproject.service.mapper.ModePaiementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ModePaiement}.
 */
@Service
@Transactional
public class ModePaiementServiceImpl implements ModePaiementService {

    private final Logger log = LoggerFactory.getLogger(ModePaiementServiceImpl.class);

    private final ModePaiementRepository modePaiementRepository;

    private final ModePaiementMapper modePaiementMapper;

    public ModePaiementServiceImpl(ModePaiementRepository modePaiementRepository, ModePaiementMapper modePaiementMapper) {
        this.modePaiementRepository = modePaiementRepository;
        this.modePaiementMapper = modePaiementMapper;
    }

    /**
     * Save a modePaiement.
     *
     * @param modePaiementDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ModePaiementDTO save(ModePaiementDTO modePaiementDTO) {
        log.debug("Request to save ModePaiement : {}", modePaiementDTO);
        ModePaiement modePaiement = modePaiementMapper.toEntity(modePaiementDTO);
        modePaiement = modePaiementRepository.save(modePaiement);
        return modePaiementMapper.toDto(modePaiement);
    }

    /**
     * Get all the modePaiements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ModePaiementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ModePaiements");
        return modePaiementRepository.findAll(pageable)
            .map(modePaiementMapper::toDto);
    }

    /**
     * Get one modePaiement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ModePaiementDTO> findOne(Long id) {
        log.debug("Request to get ModePaiement : {}", id);
        return modePaiementRepository.findById(id)
            .map(modePaiementMapper::toDto);
    }

    /**
     * Delete the modePaiement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ModePaiement : {}", id);
        modePaiementRepository.deleteById(id);
    }
}
