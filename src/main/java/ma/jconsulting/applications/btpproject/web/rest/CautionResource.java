package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.service.CautionService;
import ma.jconsulting.applications.btpproject.web.rest.errors.BadRequestAlertException;
import ma.jconsulting.applications.btpproject.service.dto.CautionDTO;

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
 * REST controller for managing {@link ma.jconsulting.applications.btpproject.domain.Caution}.
 */
@RestController
@RequestMapping("/api")
public class CautionResource {

    private final Logger log = LoggerFactory.getLogger(CautionResource.class);

    private static final String ENTITY_NAME = "caution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CautionService cautionService;

    public CautionResource(CautionService cautionService) {
        this.cautionService = cautionService;
    }

    /**
     * {@code POST  /cautions} : Create a new caution.
     *
     * @param cautionDTO the cautionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cautionDTO, or with status {@code 400 (Bad Request)} if the caution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cautions")
    public ResponseEntity<CautionDTO> createCaution(@Valid @RequestBody CautionDTO cautionDTO) throws URISyntaxException {
        log.debug("REST request to save Caution : {}", cautionDTO);
        if (cautionDTO.getId() != null) {
            throw new BadRequestAlertException("A new caution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CautionDTO result = cautionService.save(cautionDTO);
        return ResponseEntity.created(new URI("/api/cautions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cautions} : Updates an existing caution.
     *
     * @param cautionDTO the cautionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cautionDTO,
     * or with status {@code 400 (Bad Request)} if the cautionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cautionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cautions")
    public ResponseEntity<CautionDTO> updateCaution(@Valid @RequestBody CautionDTO cautionDTO) throws URISyntaxException {
        log.debug("REST request to update Caution : {}", cautionDTO);
        if (cautionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CautionDTO result = cautionService.save(cautionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cautionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cautions} : get all the cautions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cautions in body.
     */
    @GetMapping("/cautions")
    public ResponseEntity<List<CautionDTO>> getAllCautions(Pageable pageable) {
        log.debug("REST request to get a page of Cautions");
        Page<CautionDTO> page = cautionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cautions/:id} : get the "id" caution.
     *
     * @param id the id of the cautionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cautionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cautions/{id}")
    public ResponseEntity<CautionDTO> getCaution(@PathVariable Long id) {
        log.debug("REST request to get Caution : {}", id);
        Optional<CautionDTO> cautionDTO = cautionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cautionDTO);
    }

    /**
     * {@code DELETE  /cautions/:id} : delete the "id" caution.
     *
     * @param id the id of the cautionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cautions/{id}")
    public ResponseEntity<Void> deleteCaution(@PathVariable Long id) {
        log.debug("REST request to delete Caution : {}", id);
        cautionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
