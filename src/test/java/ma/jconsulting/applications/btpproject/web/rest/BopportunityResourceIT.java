package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.Bopportunity;
import ma.jconsulting.applications.btpproject.repository.BopportunityRepository;
import ma.jconsulting.applications.btpproject.service.BopportunityService;
import ma.jconsulting.applications.btpproject.service.dto.BopportunityDTO;
import ma.jconsulting.applications.btpproject.service.mapper.BopportunityMapper;
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
 * Integration tests for the {@link BopportunityResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class BopportunityResourceIT {

    private static final Long DEFAULT_ID_BOPPORTUNITY = 1L;
    private static final Long UPDATED_ID_BOPPORTUNITY = 2L;

    private static final LocalDate DEFAULT_DATE_REMISE_PLIS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REMISE_PLIS = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MONTANT_CAUTION = 1D;
    private static final Double UPDATED_MONTANT_CAUTION = 2D;

    private static final String DEFAULT_OBJET_AFFAIRE = "AAAAAAAAAA";
    private static final String UPDATED_OBJET_AFFAIRE = "BBBBBBBBBB";

    private static final Double DEFAULT_ESTIMATION_BUDGET = 1D;
    private static final Double UPDATED_ESTIMATION_BUDGET = 2D;

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_APPEL_OFFRE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_APPEL_OFFRE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_MARCHE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_MARCHE = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private BopportunityRepository bopportunityRepository;

    @Autowired
    private BopportunityMapper bopportunityMapper;

    @Autowired
    private BopportunityService bopportunityService;

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

    private MockMvc restBopportunityMockMvc;

    private Bopportunity bopportunity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BopportunityResource bopportunityResource = new BopportunityResource(bopportunityService);
        this.restBopportunityMockMvc = MockMvcBuilders.standaloneSetup(bopportunityResource)
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
    public static Bopportunity createEntity(EntityManager em) {
        Bopportunity bopportunity = new Bopportunity()
            .idBopportunity(DEFAULT_ID_BOPPORTUNITY)
            .dateRemisePlis(DEFAULT_DATE_REMISE_PLIS)
            .montantCaution(DEFAULT_MONTANT_CAUTION)
            .objetAffaire(DEFAULT_OBJET_AFFAIRE)
            .estimationBudget(DEFAULT_ESTIMATION_BUDGET)
            .commentaire(DEFAULT_COMMENTAIRE)
            .numeroAppelOffre(DEFAULT_NUMERO_APPEL_OFFRE)
            .numeroMarche(DEFAULT_NUMERO_MARCHE)
            .url(DEFAULT_URL);
        return bopportunity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bopportunity createUpdatedEntity(EntityManager em) {
        Bopportunity bopportunity = new Bopportunity()
            .idBopportunity(UPDATED_ID_BOPPORTUNITY)
            .dateRemisePlis(UPDATED_DATE_REMISE_PLIS)
            .montantCaution(UPDATED_MONTANT_CAUTION)
            .objetAffaire(UPDATED_OBJET_AFFAIRE)
            .estimationBudget(UPDATED_ESTIMATION_BUDGET)
            .commentaire(UPDATED_COMMENTAIRE)
            .numeroAppelOffre(UPDATED_NUMERO_APPEL_OFFRE)
            .numeroMarche(UPDATED_NUMERO_MARCHE)
            .url(UPDATED_URL);
        return bopportunity;
    }

    @BeforeEach
    public void initTest() {
        bopportunity = createEntity(em);
    }

    @Test
    @Transactional
    public void createBopportunity() throws Exception {
        int databaseSizeBeforeCreate = bopportunityRepository.findAll().size();

        // Create the Bopportunity
        BopportunityDTO bopportunityDTO = bopportunityMapper.toDto(bopportunity);
        restBopportunityMockMvc.perform(post("/api/bopportunities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bopportunityDTO)))
            .andExpect(status().isCreated());

        // Validate the Bopportunity in the database
        List<Bopportunity> bopportunityList = bopportunityRepository.findAll();
        assertThat(bopportunityList).hasSize(databaseSizeBeforeCreate + 1);
        Bopportunity testBopportunity = bopportunityList.get(bopportunityList.size() - 1);
        assertThat(testBopportunity.getIdBopportunity()).isEqualTo(DEFAULT_ID_BOPPORTUNITY);
        assertThat(testBopportunity.getDateRemisePlis()).isEqualTo(DEFAULT_DATE_REMISE_PLIS);
        assertThat(testBopportunity.getMontantCaution()).isEqualTo(DEFAULT_MONTANT_CAUTION);
        assertThat(testBopportunity.getObjetAffaire()).isEqualTo(DEFAULT_OBJET_AFFAIRE);
        assertThat(testBopportunity.getEstimationBudget()).isEqualTo(DEFAULT_ESTIMATION_BUDGET);
        assertThat(testBopportunity.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
        assertThat(testBopportunity.getNumeroAppelOffre()).isEqualTo(DEFAULT_NUMERO_APPEL_OFFRE);
        assertThat(testBopportunity.getNumeroMarche()).isEqualTo(DEFAULT_NUMERO_MARCHE);
        assertThat(testBopportunity.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createBopportunityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bopportunityRepository.findAll().size();

        // Create the Bopportunity with an existing ID
        bopportunity.setId(1L);
        BopportunityDTO bopportunityDTO = bopportunityMapper.toDto(bopportunity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBopportunityMockMvc.perform(post("/api/bopportunities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bopportunityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bopportunity in the database
        List<Bopportunity> bopportunityList = bopportunityRepository.findAll();
        assertThat(bopportunityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdBopportunityIsRequired() throws Exception {
        int databaseSizeBeforeTest = bopportunityRepository.findAll().size();
        // set the field null
        bopportunity.setIdBopportunity(null);

        // Create the Bopportunity, which fails.
        BopportunityDTO bopportunityDTO = bopportunityMapper.toDto(bopportunity);

        restBopportunityMockMvc.perform(post("/api/bopportunities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bopportunityDTO)))
            .andExpect(status().isBadRequest());

        List<Bopportunity> bopportunityList = bopportunityRepository.findAll();
        assertThat(bopportunityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroAppelOffreIsRequired() throws Exception {
        int databaseSizeBeforeTest = bopportunityRepository.findAll().size();
        // set the field null
        bopportunity.setNumeroAppelOffre(null);

        // Create the Bopportunity, which fails.
        BopportunityDTO bopportunityDTO = bopportunityMapper.toDto(bopportunity);

        restBopportunityMockMvc.perform(post("/api/bopportunities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bopportunityDTO)))
            .andExpect(status().isBadRequest());

        List<Bopportunity> bopportunityList = bopportunityRepository.findAll();
        assertThat(bopportunityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBopportunities() throws Exception {
        // Initialize the database
        bopportunityRepository.saveAndFlush(bopportunity);

        // Get all the bopportunityList
        restBopportunityMockMvc.perform(get("/api/bopportunities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bopportunity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idBopportunity").value(hasItem(DEFAULT_ID_BOPPORTUNITY.intValue())))
            .andExpect(jsonPath("$.[*].dateRemisePlis").value(hasItem(DEFAULT_DATE_REMISE_PLIS.toString())))
            .andExpect(jsonPath("$.[*].montantCaution").value(hasItem(DEFAULT_MONTANT_CAUTION.doubleValue())))
            .andExpect(jsonPath("$.[*].objetAffaire").value(hasItem(DEFAULT_OBJET_AFFAIRE)))
            .andExpect(jsonPath("$.[*].estimationBudget").value(hasItem(DEFAULT_ESTIMATION_BUDGET.doubleValue())))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)))
            .andExpect(jsonPath("$.[*].numeroAppelOffre").value(hasItem(DEFAULT_NUMERO_APPEL_OFFRE)))
            .andExpect(jsonPath("$.[*].numeroMarche").value(hasItem(DEFAULT_NUMERO_MARCHE)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }
    
    @Test
    @Transactional
    public void getBopportunity() throws Exception {
        // Initialize the database
        bopportunityRepository.saveAndFlush(bopportunity);

        // Get the bopportunity
        restBopportunityMockMvc.perform(get("/api/bopportunities/{id}", bopportunity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bopportunity.getId().intValue()))
            .andExpect(jsonPath("$.idBopportunity").value(DEFAULT_ID_BOPPORTUNITY.intValue()))
            .andExpect(jsonPath("$.dateRemisePlis").value(DEFAULT_DATE_REMISE_PLIS.toString()))
            .andExpect(jsonPath("$.montantCaution").value(DEFAULT_MONTANT_CAUTION.doubleValue()))
            .andExpect(jsonPath("$.objetAffaire").value(DEFAULT_OBJET_AFFAIRE))
            .andExpect(jsonPath("$.estimationBudget").value(DEFAULT_ESTIMATION_BUDGET.doubleValue()))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE))
            .andExpect(jsonPath("$.numeroAppelOffre").value(DEFAULT_NUMERO_APPEL_OFFRE))
            .andExpect(jsonPath("$.numeroMarche").value(DEFAULT_NUMERO_MARCHE))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }

    @Test
    @Transactional
    public void getNonExistingBopportunity() throws Exception {
        // Get the bopportunity
        restBopportunityMockMvc.perform(get("/api/bopportunities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBopportunity() throws Exception {
        // Initialize the database
        bopportunityRepository.saveAndFlush(bopportunity);

        int databaseSizeBeforeUpdate = bopportunityRepository.findAll().size();

        // Update the bopportunity
        Bopportunity updatedBopportunity = bopportunityRepository.findById(bopportunity.getId()).get();
        // Disconnect from session so that the updates on updatedBopportunity are not directly saved in db
        em.detach(updatedBopportunity);
        updatedBopportunity
            .idBopportunity(UPDATED_ID_BOPPORTUNITY)
            .dateRemisePlis(UPDATED_DATE_REMISE_PLIS)
            .montantCaution(UPDATED_MONTANT_CAUTION)
            .objetAffaire(UPDATED_OBJET_AFFAIRE)
            .estimationBudget(UPDATED_ESTIMATION_BUDGET)
            .commentaire(UPDATED_COMMENTAIRE)
            .numeroAppelOffre(UPDATED_NUMERO_APPEL_OFFRE)
            .numeroMarche(UPDATED_NUMERO_MARCHE)
            .url(UPDATED_URL);
        BopportunityDTO bopportunityDTO = bopportunityMapper.toDto(updatedBopportunity);

        restBopportunityMockMvc.perform(put("/api/bopportunities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bopportunityDTO)))
            .andExpect(status().isOk());

        // Validate the Bopportunity in the database
        List<Bopportunity> bopportunityList = bopportunityRepository.findAll();
        assertThat(bopportunityList).hasSize(databaseSizeBeforeUpdate);
        Bopportunity testBopportunity = bopportunityList.get(bopportunityList.size() - 1);
        assertThat(testBopportunity.getIdBopportunity()).isEqualTo(UPDATED_ID_BOPPORTUNITY);
        assertThat(testBopportunity.getDateRemisePlis()).isEqualTo(UPDATED_DATE_REMISE_PLIS);
        assertThat(testBopportunity.getMontantCaution()).isEqualTo(UPDATED_MONTANT_CAUTION);
        assertThat(testBopportunity.getObjetAffaire()).isEqualTo(UPDATED_OBJET_AFFAIRE);
        assertThat(testBopportunity.getEstimationBudget()).isEqualTo(UPDATED_ESTIMATION_BUDGET);
        assertThat(testBopportunity.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
        assertThat(testBopportunity.getNumeroAppelOffre()).isEqualTo(UPDATED_NUMERO_APPEL_OFFRE);
        assertThat(testBopportunity.getNumeroMarche()).isEqualTo(UPDATED_NUMERO_MARCHE);
        assertThat(testBopportunity.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingBopportunity() throws Exception {
        int databaseSizeBeforeUpdate = bopportunityRepository.findAll().size();

        // Create the Bopportunity
        BopportunityDTO bopportunityDTO = bopportunityMapper.toDto(bopportunity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBopportunityMockMvc.perform(put("/api/bopportunities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bopportunityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bopportunity in the database
        List<Bopportunity> bopportunityList = bopportunityRepository.findAll();
        assertThat(bopportunityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBopportunity() throws Exception {
        // Initialize the database
        bopportunityRepository.saveAndFlush(bopportunity);

        int databaseSizeBeforeDelete = bopportunityRepository.findAll().size();

        // Delete the bopportunity
        restBopportunityMockMvc.perform(delete("/api/bopportunities/{id}", bopportunity.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bopportunity> bopportunityList = bopportunityRepository.findAll();
        assertThat(bopportunityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
