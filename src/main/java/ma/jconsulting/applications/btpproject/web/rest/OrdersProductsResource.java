package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.service.OrdersProductsService;
import ma.jconsulting.applications.btpproject.web.rest.errors.BadRequestAlertException;
import ma.jconsulting.applications.btpproject.service.dto.OrdersProductsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ma.jconsulting.applications.btpproject.domain.OrdersProducts}.
 */
@RestController
@RequestMapping("/api")
public class OrdersProductsResource {

    private final Logger log = LoggerFactory.getLogger(OrdersProductsResource.class);

    private static final String ENTITY_NAME = "ordersProducts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdersProductsService ordersProductsService;

    public OrdersProductsResource(OrdersProductsService ordersProductsService) {
        this.ordersProductsService = ordersProductsService;
    }

    /**
     * {@code POST  /orders-products} : Create a new ordersProducts.
     *
     * @param ordersProductsDTO the ordersProductsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ordersProductsDTO, or with status {@code 400 (Bad Request)} if the ordersProducts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/orders-products")
    public ResponseEntity<OrdersProductsDTO> createOrdersProducts(@Valid @RequestBody OrdersProductsDTO ordersProductsDTO) throws URISyntaxException {
        log.debug("REST request to save OrdersProducts : {}", ordersProductsDTO);
        if (ordersProductsDTO.getId() != null) {
            throw new BadRequestAlertException("A new ordersProducts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrdersProductsDTO result = ordersProductsService.save(ordersProductsDTO);
        return ResponseEntity.created(new URI("/api/orders-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /orders-products} : Updates an existing ordersProducts.
     *
     * @param ordersProductsDTO the ordersProductsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ordersProductsDTO,
     * or with status {@code 400 (Bad Request)} if the ordersProductsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ordersProductsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/orders-products")
    public ResponseEntity<OrdersProductsDTO> updateOrdersProducts(@Valid @RequestBody OrdersProductsDTO ordersProductsDTO) throws URISyntaxException {
        log.debug("REST request to update OrdersProducts : {}", ordersProductsDTO);
        if (ordersProductsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrdersProductsDTO result = ordersProductsService.save(ordersProductsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ordersProductsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /orders-products} : get all the ordersProducts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordersProducts in body.
     */
    @GetMapping("/orders-products")
    public ResponseEntity<List<OrdersProductsDTO>> getAllOrdersProducts(Pageable pageable) {
        log.debug("REST request to get a page of OrdersProducts");
        Page<OrdersProductsDTO> page = ordersProductsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /orders-products/:id} : get the "id" ordersProducts.
     *
     * @param id the id of the ordersProductsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ordersProductsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orders-products/{id}")
    public ResponseEntity<OrdersProductsDTO> getOrdersProducts(@PathVariable Long id) {
        log.debug("REST request to get OrdersProducts : {}", id);
        Optional<OrdersProductsDTO> ordersProductsDTO = ordersProductsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ordersProductsDTO);
    }

    /**
     * {@code DELETE  /orders-products/:id} : delete the "id" ordersProducts.
     *
     * @param id the id of the ordersProductsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orders-products/{id}")
    public ResponseEntity<Void> deleteOrdersProducts(@PathVariable Long id) {
        log.debug("REST request to delete OrdersProducts : {}", id);
        ordersProductsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
