package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.OrdersServicesService;
import ma.jconsulting.applications.btpproject.domain.OrdersServices;
import ma.jconsulting.applications.btpproject.repository.OrdersServicesRepository;
import ma.jconsulting.applications.btpproject.service.dto.OrdersServicesDTO;
import ma.jconsulting.applications.btpproject.service.mapper.OrdersServicesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrdersServices}.
 */
@Service
@Transactional
public class OrdersServicesServiceImpl implements OrdersServicesService {

    private final Logger log = LoggerFactory.getLogger(OrdersServicesServiceImpl.class);

    private final OrdersServicesRepository ordersServicesRepository;

    private final OrdersServicesMapper ordersServicesMapper;

    public OrdersServicesServiceImpl(OrdersServicesRepository ordersServicesRepository, OrdersServicesMapper ordersServicesMapper) {
        this.ordersServicesRepository = ordersServicesRepository;
        this.ordersServicesMapper = ordersServicesMapper;
    }

    /**
     * Save a ordersServices.
     *
     * @param ordersServicesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrdersServicesDTO save(OrdersServicesDTO ordersServicesDTO) {
        log.debug("Request to save OrdersServices : {}", ordersServicesDTO);
        OrdersServices ordersServices = ordersServicesMapper.toEntity(ordersServicesDTO);
        ordersServices = ordersServicesRepository.save(ordersServices);
        return ordersServicesMapper.toDto(ordersServices);
    }

    /**
     * Get all the ordersServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrdersServicesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrdersServices");
        return ordersServicesRepository.findAll(pageable)
            .map(ordersServicesMapper::toDto);
    }

    /**
     * Get one ordersServices by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrdersServicesDTO> findOne(Long id) {
        log.debug("Request to get OrdersServices : {}", id);
        return ordersServicesRepository.findById(id)
            .map(ordersServicesMapper::toDto);
    }

    /**
     * Delete the ordersServices by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrdersServices : {}", id);
        ordersServicesRepository.deleteById(id);
    }
}
