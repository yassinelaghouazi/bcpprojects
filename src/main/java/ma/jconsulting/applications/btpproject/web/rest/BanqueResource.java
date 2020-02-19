package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.service.BanqueService;
import ma.jconsulting.applications.btpproject.web.rest.errors.BadRequestAlertException;
import ma.jconsulting.applications.btpproject.service.dto.BanqueDTO;

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
 * REST controller for managing {@link ma.jconsulting.applications.btpproject.domain.Banque}.
 */
@RestController
@RequestMapping("/api")
public class BanqueResource {

    private final Logger log = LoggerFactory.getLogger(BanqueResource.class);

    private static final String ENTITY_NAME = "banque";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BanqueService banqueService;

    public BanqueResource(BanqueService banqueService) {
        this.banqueService = banqueService;
    }

    /**
     * {@code POST  /banques} : Create a new banque.
     *
     * @param banqueDTO the banqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new banqueDTO, or with status {@code 400 (Bad Request)} if the banque has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/banques")
    public ResponseEntity<BanqueDTO> createBanque(@Valid @RequestBody BanqueDTO banqueDTO) throws URISyntaxException {
        log.debug("REST request to save Banque : {}", banqueDTO);
        if (banqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new banque cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BanqueDTO result = banqueService.save(banqueDTO);
        return ResponseEntity.created(new URI("/api/banques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /banques} : Updates an existing banque.
     *
     * @param banqueDTO the banqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated banqueDTO,
     * or with status {@code 400 (Bad Request)} if the banqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the banqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/banques")
    public ResponseEntity<BanqueDTO> updateBanque(@Valid @RequestBody BanqueDTO banqueDTO) throws URISyntaxException {
        log.debug("REST request to update Banque : {}", banqueDTO);
        if (banqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BanqueDTO result = banqueService.save(banqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, banqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /banques} : get all the banques.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of banques in body.
     */
    @GetMapping("/banques")
    public ResponseEntity<List<BanqueDTO>> getAllBanques(Pageable pageable) {
        log.debug("REST request to get a page of Banques");
        Page<BanqueDTO> page = banqueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /banques/:id} : get the "id" banque.
     *
     * @param id the id of the banqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the banqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/banques/{id}")
    public ResponseEntity<BanqueDTO> getBanque(@PathVariable Long id) {
        log.debug("REST request to get Banque : {}", id);
        Optional<BanqueDTO> banqueDTO = banqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(banqueDTO);
    }

    /**
     * {@code DELETE  /banques/:id} : delete the "id" banque.
     *
     * @param id the id of the banqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/banques/{id}")
    public ResponseEntity<Void> deleteBanque(@PathVariable Long id) {
        log.debug("REST request to delete Banque : {}", id);
        banqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
