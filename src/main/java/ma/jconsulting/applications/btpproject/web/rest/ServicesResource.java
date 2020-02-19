package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.service.ServicesService;
import ma.jconsulting.applications.btpproject.web.rest.errors.BadRequestAlertException;
import ma.jconsulting.applications.btpproject.service.dto.ServicesDTO;

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
 * REST controller for managing {@link ma.jconsulting.applications.btpproject.domain.Services}.
 */
@RestController
@RequestMapping("/api")
public class ServicesResource {

    private final Logger log = LoggerFactory.getLogger(ServicesResource.class);

    private static final String ENTITY_NAME = "services";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServicesService servicesService;

    public ServicesResource(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    /**
     * {@code POST  /services} : Create a new services.
     *
     * @param servicesDTO the servicesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new servicesDTO, or with status {@code 400 (Bad Request)} if the services has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/services")
    public ResponseEntity<ServicesDTO> createServices(@Valid @RequestBody ServicesDTO servicesDTO) throws URISyntaxException {
        log.debug("REST request to save Services : {}", servicesDTO);
        if (servicesDTO.getId() != null) {
            throw new BadRequestAlertException("A new services cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServicesDTO result = servicesService.save(servicesDTO);
        return ResponseEntity.created(new URI("/api/services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /services} : Updates an existing services.
     *
     * @param servicesDTO the servicesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servicesDTO,
     * or with status {@code 400 (Bad Request)} if the servicesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the servicesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/services")
    public ResponseEntity<ServicesDTO> updateServices(@Valid @RequestBody ServicesDTO servicesDTO) throws URISyntaxException {
        log.debug("REST request to update Services : {}", servicesDTO);
        if (servicesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServicesDTO result = servicesService.save(servicesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, servicesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /services} : get all the services.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of services in body.
     */
    @GetMapping("/services")
    public ResponseEntity<List<ServicesDTO>> getAllServices(Pageable pageable) {
        log.debug("REST request to get a page of Services");
        Page<ServicesDTO> page = servicesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /services/:id} : get the "id" services.
     *
     * @param id the id of the servicesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the servicesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/services/{id}")
    public ResponseEntity<ServicesDTO> getServices(@PathVariable Long id) {
        log.debug("REST request to get Services : {}", id);
        Optional<ServicesDTO> servicesDTO = servicesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(servicesDTO);
    }

    /**
     * {@code DELETE  /services/:id} : delete the "id" services.
     *
     * @param id the id of the servicesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> deleteServices(@PathVariable Long id) {
        log.debug("REST request to delete Services : {}", id);
        servicesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
