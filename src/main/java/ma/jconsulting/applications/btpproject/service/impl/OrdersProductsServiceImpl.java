package ma.jconsulting.applications.btpproject.service.impl;

import ma.jconsulting.applications.btpproject.service.OrdersProductsService;
import ma.jconsulting.applications.btpproject.domain.OrdersProducts;
import ma.jconsulting.applications.btpproject.repository.OrdersProductsRepository;
import ma.jconsulting.applications.btpproject.service.dto.OrdersProductsDTO;
import ma.jconsulting.applications.btpproject.service.mapper.OrdersProductsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrdersProducts}.
 */
@Service
@Transactional
public class OrdersProductsServiceImpl implements OrdersProductsService {

    private final Logger log = LoggerFactory.getLogger(OrdersProductsServiceImpl.class);

    private final OrdersProductsRepository ordersProductsRepository;

    private final OrdersProductsMapper ordersProductsMapper;

    public OrdersProductsServiceImpl(OrdersProductsRepository ordersProductsRepository, OrdersProductsMapper ordersProductsMapper) {
        this.ordersProductsRepository = ordersProductsRepository;
        this.ordersProductsMapper = ordersProductsMapper;
    }

    /**
     * Save a ordersProducts.
     *
     * @param ordersProductsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrdersProductsDTO save(OrdersProductsDTO ordersProductsDTO) {
        log.debug("Request to save OrdersProducts : {}", ordersProductsDTO);
        OrdersProducts ordersProducts = ordersProductsMapper.toEntity(ordersProductsDTO);
        ordersProducts = ordersProductsRepository.save(ordersProducts);
        return ordersProductsMapper.toDto(ordersProducts);
    }

    /**
     * Get all the ordersProducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrdersProductsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrdersProducts");
        return ordersProductsRepository.findAll(pageable)
            .map(ordersProductsMapper::toDto);
    }

    /**
     * Get one ordersProducts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrdersProductsDTO> findOne(Long id) {
        log.debug("Request to get OrdersProducts : {}", id);
        return ordersProductsRepository.findById(id)
            .map(ordersProductsMapper::toDto);
    }

    /**
     * Delete the ordersProducts by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrdersProducts : {}", id);
        ordersProductsRepository.deleteById(id);
    }
}
