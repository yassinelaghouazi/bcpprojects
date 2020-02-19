package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.DeliveryService;
import ma.jconsulting.applications.btpproject.domain.Delivery;
import ma.jconsulting.applications.btpproject.repository.DeliveryRepository;
import ma.jconsulting.applications.btpproject.service.dto.DeliveryDTO;
import ma.jconsulting.applications.btpproject.service.mapper.DeliveryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Delivery}.
 */
@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private final Logger log = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    private final DeliveryRepository deliveryRepository;

    private final DeliveryMapper deliveryMapper;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
    }

    /**
     * Save a delivery.
     *
     * @param deliveryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DeliveryDTO save(DeliveryDTO deliveryDTO) {
        log.debug("Request to save Delivery : {}", deliveryDTO);
        Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
        delivery = deliveryRepository.save(delivery);
        return deliveryMapper.toDto(delivery);
    }

    /**
     * Get all the deliveries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Deliveries");
        return deliveryRepository.findAll(pageable)
            .map(deliveryMapper::toDto);
    }

    /**
     * Get one delivery by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryDTO> findOne(Long id) {
        log.debug("Request to get Delivery : {}", id);
        return deliveryRepository.findById(id)
            .map(deliveryMapper::toDto);
    }

    /**
     * Delete the delivery by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Delivery : {}", id);
        deliveryRepository.deleteById(id);
    }
}
