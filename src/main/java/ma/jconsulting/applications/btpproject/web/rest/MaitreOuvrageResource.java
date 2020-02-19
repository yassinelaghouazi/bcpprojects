package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.service.MaitreOuvrageService;
import ma.jconsulting.applications.btpproject.web.rest.errors.BadRequestAlertException;
import ma.jconsulting.applications.btpproject.service.dto.MaitreOuvrageDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ma.jconsulting.applications.btpproject.domain.MaitreOuvrage}.
 */
@RestController
@RequestMapping("/api")
public class MaitreOuvrageResource {

    private final Logger log = LoggerFactory.getLogger(MaitreOuvrageResource.class);

    private static final String ENTITY_NAME = "maitreOuvrage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MaitreOuvrageService maitreOuvrageService;

    public MaitreOuvrageResource(MaitreOuvrageService maitreOuvrageService) {
        this.maitreOuvrageService = maitreOuvrageService;
    }

    /**
     * {@code POST  /maitre-ouvrages} : Create a new maitreOuvrage.
     *
     * @param maitreOuvrageDTO the maitreOuvrageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new maitreOuvrageDTO, or with status {@code 400 (Bad Request)} if the maitreOuvrage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/maitre-ouvrages")
    public ResponseEntity<MaitreOuvrageDTO> createMaitreOuvrage(@RequestBody MaitreOuvrageDTO maitreOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to save MaitreOuvrage : {}", maitreOuvrageDTO);
        if (maitreOuvrageDTO.getId() != null) {
            throw new BadRequestAlertException("A new maitreOuvrage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MaitreOuvrageDTO result = maitreOuvrageService.save(maitreOuvrageDTO);
        return ResponseEntity.created(new URI("/api/maitre-ouvrages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /maitre-ouvrages} : Updates an existing maitreOuvrage.
     *
     * @param maitreOuvrageDTO the maitreOuvrageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated maitreOuvrageDTO,
     * or with status {@code 400 (Bad Request)} if the maitreOuvrageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the maitreOuvrageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/maitre-ouvrages")
    public ResponseEntity<MaitreOuvrageDTO> updateMaitreOuvrage(@RequestBody MaitreOuvrageDTO maitreOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to update MaitreOuvrage : {}", maitreOuvrageDTO);
        if (maitreOuvrageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MaitreOuvrageDTO result = maitreOuvrageService.save(maitreOuvrageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, maitreOuvrageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /maitre-ouvrages} : get all the maitreOuvrages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of maitreOuvrages in body.
     */
    @GetMapping("/maitre-ouvrages")
    public ResponseEntity<List<MaitreOuvrageDTO>> getAllMaitreOuvrages(Pageable pageable) {
        log.debug("REST request to get a page of MaitreOuvrages");
        Page<MaitreOuvrageDTO> page = maitreOuvrageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /maitre-ouvrages/:id} : get the "id" maitreOuvrage.
     *
     * @param id the id of the maitreOuvrageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the maitreOuvrageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/maitre-ouvrages/{id}")
    public ResponseEntity<MaitreOuvrageDTO> getMaitreOuvrage(@PathVariable Long id) {
        log.debug("REST request to get MaitreOuvrage : {}", id);
        Optional<MaitreOuvrageDTO> maitreOuvrageDTO = maitreOuvrageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(maitreOuvrageDTO);
    }

    /**
     * {@code DELETE  /maitre-ouvrages/:id} : delete the "id" maitreOuvrage.
     *
     * @param id the id of the maitreOuvrageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/maitre-ouvrages/{id}")
    public ResponseEntity<Void> deleteMaitreOuvrage(@PathVariable Long id) {
        log.debug("REST request to delete MaitreOuvrage : {}", id);
        maitreOuvrageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
