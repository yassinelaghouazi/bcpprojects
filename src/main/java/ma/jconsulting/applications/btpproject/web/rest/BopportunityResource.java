package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.service.BopportunityService;
import ma.jconsulting.applications.btpproject.web.rest.errors.BadRequestAlertException;
import ma.jconsulting.applications.btpproject.service.dto.BopportunityDTO;

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
 * REST controller for managing {@link ma.jconsulting.applications.btpproject.domain.Bopportunity}.
 */
@RestController
@RequestMapping("/api")
public class BopportunityResource {

    private final Logger log = LoggerFactory.getLogger(BopportunityResource.class);

    private static final String ENTITY_NAME = "bopportunity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BopportunityService bopportunityService;

    public BopportunityResource(BopportunityService bopportunityService) {
        this.bopportunityService = bopportunityService;
    }

    /**
     * {@code POST  /bopportunities} : Create a new bopportunity.
     *
     * @param bopportunityDTO the bopportunityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bopportunityDTO, or with status {@code 400 (Bad Request)} if the bopportunity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bopportunities")
    public ResponseEntity<BopportunityDTO> createBopportunity(@Valid @RequestBody BopportunityDTO bopportunityDTO) throws URISyntaxException {
        log.debug("REST request to save Bopportunity : {}", bopportunityDTO);
        if (bopportunityDTO.getId() != null) {
            throw new BadRequestAlertException("A new bopportunity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BopportunityDTO result = bopportunityService.save(bopportunityDTO);
        return ResponseEntity.created(new URI("/api/bopportunities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bopportunities} : Updates an existing bopportunity.
     *
     * @param bopportunityDTO the bopportunityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bopportunityDTO,
     * or with status {@code 400 (Bad Request)} if the bopportunityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bopportunityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bopportunities")
    public ResponseEntity<BopportunityDTO> updateBopportunity(@Valid @RequestBody BopportunityDTO bopportunityDTO) throws URISyntaxException {
        log.debug("REST request to update Bopportunity : {}", bopportunityDTO);
        if (bopportunityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BopportunityDTO result = bopportunityService.save(bopportunityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bopportunityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bopportunities} : get all the bopportunities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bopportunities in body.
     */
    @GetMapping("/bopportunities")
    public ResponseEntity<List<BopportunityDTO>> getAllBopportunities(Pageable pageable) {
        log.debug("REST request to get a page of Bopportunities");
        Page<BopportunityDTO> page = bopportunityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bopportunities/:id} : get the "id" bopportunity.
     *
     * @param id the id of the bopportunityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bopportunityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bopportunities/{id}")
    public ResponseEntity<BopportunityDTO> getBopportunity(@PathVariable Long id) {
        log.debug("REST request to get Bopportunity : {}", id);
        Optional<BopportunityDTO> bopportunityDTO = bopportunityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bopportunityDTO);
    }

    /**
     * {@code DELETE  /bopportunities/:id} : delete the "id" bopportunity.
     *
     * @param id the id of the bopportunityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bopportunities/{id}")
    public ResponseEntity<Void> deleteBopportunity(@PathVariable Long id) {
        log.debug("REST request to delete Bopportunity : {}", id);
        bopportunityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
