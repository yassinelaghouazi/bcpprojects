package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.Reglement;
import ma.jconsulting.applications.btpproject.repository.ReglementRepository;
import ma.jconsulting.applications.btpproject.service.ReglementService;
import ma.jconsulting.applications.btpproject.service.dto.ReglementDTO;
import ma.jconsulting.applications.btpproject.service.mapper.ReglementMapper;
import ma.jconsulting.applications.btpproject.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static ma.jconsulting.applications.btpproject.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ReglementResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class ReglementResourceIT {

    private static final Long DEFAULT_ID_REGLEMENT = 1L;
    private static final Long UPDATED_ID_REGLEMENT = 2L;

    private static final LocalDate DEFAULT_DATE_EFFET_REGLEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EFFET_REGLEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_REGLEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REGLEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MONTANT_REGLEMENT = 1D;
    private static final Double UPDATED_MONTANT_REGLEMENT = 2D;

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    @Autowired
    private ReglementRepository reglementRepository;

    @Autowired
    private ReglementMapper reglementMapper;

    @Autowired
    private ReglementService reglementService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restReglementMockMvc;

    private Reglement reglement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReglementResource reglementResource = new ReglementResource(reglementService);
        this.restReglementMockMvc = MockMvcBuilders.standaloneSetup(reglementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reglement createEntity(EntityManager em) {
        Reglement reglement = new Reglement()
            .idReglement(DEFAULT_ID_REGLEMENT)
            .dateEffetReglement(DEFAULT_DATE_EFFET_REGLEMENT)
            .dateReglement(DEFAULT_DATE_REGLEMENT)
            .montantReglement(DEFAULT_MONTANT_REGLEMENT)
            .commentaire(DEFAULT_COMMENTAIRE);
        return reglement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reglement createUpdatedEntity(EntityManager em) {
        Reglement reglement = new Reglement()
            .idReglement(UPDATED_ID_REGLEMENT)
            .dateEffetReglement(UPDATED_DATE_EFFET_REGLEMENT)
            .dateReglement(UPDATED_DATE_REGLEMENT)
            .montantReglement(UPDATED_MONTANT_REGLEMENT)
            .commentaire(UPDATED_COMMENTAIRE);
        return reglement;
    }

    @BeforeEach
    public void initTest() {
        reglement = createEntity(em);
    }

    @Test
    @Transactional
    public void createReglement() throws Exception {
        int databaseSizeBeforeCreate = reglementRepository.findAll().size();

        // Create the Reglement
        ReglementDTO reglementDTO = reglementMapper.toDto(reglement);
        restReglementMockMvc.perform(post("/api/reglements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementDTO)))
            .andExpect(status().isCreated());

        // Validate the Reglement in the database
        List<Reglement> reglementList = reglementRepository.findAll();
        assertThat(reglementList).hasSize(databaseSizeBeforeCreate + 1);
        Reglement testReglement = reglementList.get(reglementList.size() - 1);
        assertThat(testReglement.getIdReglement()).isEqualTo(DEFAULT_ID_REGLEMENT);
        assertThat(testReglement.getDateEffetReglement()).isEqualTo(DEFAULT_DATE_EFFET_REGLEMENT);
        assertThat(testReglement.getDateReglement()).isEqualTo(DEFAULT_DATE_REGLEMENT);
        assertThat(testReglement.getMontantReglement()).isEqualTo(DEFAULT_MONTANT_REGLEMENT);
        assertThat(testReglement.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void createReglementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reglementRepository.findAll().size();

        // Create the Reglement with an existing ID
        reglement.setId(1L);
        ReglementDTO reglementDTO = reglementMapper.toDto(reglement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReglementMockMvc.perform(post("/api/reglements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reglement in the database
        List<Reglement> reglementList = reglementRepository.findAll();
        assertThat(reglementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdReglementIsRequired() throws Exception {
        int databaseSizeBeforeTest = reglementRepository.findAll().size();
        // set the field null
        reglement.setIdReglement(null);

        // Create the Reglement, which fails.
        ReglementDTO reglementDTO = reglementMapper.toDto(reglement);

        restReglementMockMvc.perform(post("/api/reglements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementDTO)))
            .andExpect(status().isBadRequest());

        List<Reglement> reglementList = reglementRepository.findAll();
        assertThat(reglementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReglements() throws Exception {
        // Initialize the database
        reglementRepository.saveAndFlush(reglement);

        // Get all the reglementList
        restReglementMockMvc.perform(get("/api/reglements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reglement.getId().intValue())))
            .andExpect(jsonPath("$.[*].idReglement").value(hasItem(DEFAULT_ID_REGLEMENT.intValue())))
            .andExpect(jsonPath("$.[*].dateEffetReglement").value(hasItem(DEFAULT_DATE_EFFET_REGLEMENT.toString())))
            .andExpect(jsonPath("$.[*].dateReglement").value(hasItem(DEFAULT_DATE_REGLEMENT.toString())))
            .andExpect(jsonPath("$.[*].montantReglement").value(hasItem(DEFAULT_MONTANT_REGLEMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)));
    }
    
    @Test
    @Transactional
    public void getReglement() throws Exception {
        // Initialize the database
        reglementRepository.saveAndFlush(reglement);

        // Get the reglement
        restReglementMockMvc.perform(get("/api/reglements/{id}", reglement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reglement.getId().intValue()))
            .andExpect(jsonPath("$.idReglement").value(DEFAULT_ID_REGLEMENT.intValue()))
            .andExpect(jsonPath("$.dateEffetReglement").value(DEFAULT_DATE_EFFET_REGLEMENT.toString()))
            .andExpect(jsonPath("$.dateReglement").value(DEFAULT_DATE_REGLEMENT.toString()))
            .andExpect(jsonPath("$.montantReglement").value(DEFAULT_MONTANT_REGLEMENT.doubleValue()))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE));
    }

    @Test
    @Transactional
    public void getNonExistingReglement() throws Exception {
        // Get the reglement
        restReglementMockMvc.perform(get("/api/reglements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReglement() throws Exception {
        // Initialize the database
        reglementRepository.saveAndFlush(reglement);

        int databaseSizeBeforeUpdate = reglementRepository.findAll().size();

        // Update the reglement
        Reglement updatedReglement = reglementRepository.findById(reglement.getId()).get();
        // Disconnect from session so that the updates on updatedReglement are not directly saved in db
        em.detach(updatedReglement);
        updatedReglement
            .idReglement(UPDATED_ID_REGLEMENT)
            .dateEffetReglement(UPDATED_DATE_EFFET_REGLEMENT)
            .dateReglement(UPDATED_DATE_REGLEMENT)
            .montantReglement(UPDATED_MONTANT_REGLEMENT)
            .commentaire(UPDATED_COMMENTAIRE);
        ReglementDTO reglementDTO = reglementMapper.toDto(updatedReglement);

        restReglementMockMvc.perform(put("/api/reglements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementDTO)))
            .andExpect(status().isOk());

        // Validate the Reglement in the database
        List<Reglement> reglementList = reglementRepository.findAll();
        assertThat(reglementList).hasSize(databaseSizeBeforeUpdate);
        Reglement testReglement = reglementList.get(reglementList.size() - 1);
        assertThat(testReglement.getIdReglement()).isEqualTo(UPDATED_ID_REGLEMENT);
        assertThat(testReglement.getDateEffetReglement()).isEqualTo(UPDATED_DATE_EFFET_REGLEMENT);
        assertThat(testReglement.getDateReglement()).isEqualTo(UPDATED_DATE_REGLEMENT);
        assertThat(testReglement.getMontantReglement()).isEqualTo(UPDATED_MONTANT_REGLEMENT);
        assertThat(testReglement.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingReglement() throws Exception {
        int databaseSizeBeforeUpdate = reglementRepository.findAll().size();

        // Create the Reglement
        ReglementDTO reglementDTO = reglementMapper.toDto(reglement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReglementMockMvc.perform(put("/api/reglements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reglement in the database
        List<Reglement> reglementList = reglementRepository.findAll();
        assertThat(reglementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReglement() throws Exception {
        // Initialize the database
        reglementRepository.saveAndFlush(reglement);

        int databaseSizeBeforeDelete = reglementRepository.findAll().size();

        // Delete the reglement
        restReglementMockMvc.perform(delete("/api/reglements/{id}", reglement.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Reglement> reglementList = reglementRepository.findAll();
        assertThat(reglementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
