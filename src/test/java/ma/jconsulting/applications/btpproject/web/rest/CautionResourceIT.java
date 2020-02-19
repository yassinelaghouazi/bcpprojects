package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.Caution;
import ma.jconsulting.applications.btpproject.repository.CautionRepository;
import ma.jconsulting.applications.btpproject.service.CautionService;
import ma.jconsulting.applications.btpproject.service.dto.CautionDTO;
import ma.jconsulting.applications.btpproject.service.mapper.CautionMapper;
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

import ma.jconsulting.applications.btpproject.domain.enumeration.StatusCaution;
import ma.jconsulting.applications.btpproject.domain.enumeration.TypeCaution;
/**
 * Integration tests for the {@link CautionResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class CautionResourceIT {

    private static final Long DEFAULT_IDCAUTION = 1L;
    private static final Long UPDATED_IDCAUTION = 2L;

    private static final Double DEFAULT_MONTANT_CAUTION = 1D;
    private static final Double UPDATED_MONTANT_CAUTION = 2D;

    private static final String DEFAULT_OBJET = "AAAAAAAAAA";
    private static final String UPDATED_OBJET = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_CAUTION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CAUTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEMANDE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEMANDE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RETRAIT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RETRAIT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_DEPOT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEPOT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NUMERO_MARCHE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_MARCHE = "BBBBBBBBBB";

    private static final StatusCaution DEFAULT_STATUS_CAUTION = StatusCaution.DEMANDEE;
    private static final StatusCaution UPDATED_STATUS_CAUTION = StatusCaution.DEPOSEE;

    private static final TypeCaution DEFAULT_TYPE_CAUTION = TypeCaution.PROVISOIRE;
    private static final TypeCaution UPDATED_TYPE_CAUTION = TypeCaution.DEFINITIVE;

    @Autowired
    private CautionRepository cautionRepository;

    @Autowired
    private CautionMapper cautionMapper;

    @Autowired
    private CautionService cautionService;

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

    private MockMvc restCautionMockMvc;

    private Caution caution;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CautionResource cautionResource = new CautionResource(cautionService);
        this.restCautionMockMvc = MockMvcBuilders.standaloneSetup(cautionResource)
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
    public static Caution createEntity(EntityManager em) {
        Caution caution = new Caution()
            .idcaution(DEFAULT_IDCAUTION)
            .montantCaution(DEFAULT_MONTANT_CAUTION)
            .objet(DEFAULT_OBJET)
            .numeroCaution(DEFAULT_NUMERO_CAUTION)
            .dateDemande(DEFAULT_DATE_DEMANDE)
            .dateRetrait(DEFAULT_DATE_RETRAIT)
            .dateDepot(DEFAULT_DATE_DEPOT)
            .numeroMarche(DEFAULT_NUMERO_MARCHE)
            .statusCaution(DEFAULT_STATUS_CAUTION)
            .typeCaution(DEFAULT_TYPE_CAUTION);
        return caution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Caution createUpdatedEntity(EntityManager em) {
        Caution caution = new Caution()
            .idcaution(UPDATED_IDCAUTION)
            .montantCaution(UPDATED_MONTANT_CAUTION)
            .objet(UPDATED_OBJET)
            .numeroCaution(UPDATED_NUMERO_CAUTION)
            .dateDemande(UPDATED_DATE_DEMANDE)
            .dateRetrait(UPDATED_DATE_RETRAIT)
            .dateDepot(UPDATED_DATE_DEPOT)
            .numeroMarche(UPDATED_NUMERO_MARCHE)
            .statusCaution(UPDATED_STATUS_CAUTION)
            .typeCaution(UPDATED_TYPE_CAUTION);
        return caution;
    }

    @BeforeEach
    public void initTest() {
        caution = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaution() throws Exception {
        int databaseSizeBeforeCreate = cautionRepository.findAll().size();

        // Create the Caution
        CautionDTO cautionDTO = cautionMapper.toDto(caution);
        restCautionMockMvc.perform(post("/api/cautions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cautionDTO)))
            .andExpect(status().isCreated());

        // Validate the Caution in the database
        List<Caution> cautionList = cautionRepository.findAll();
        assertThat(cautionList).hasSize(databaseSizeBeforeCreate + 1);
        Caution testCaution = cautionList.get(cautionList.size() - 1);
        assertThat(testCaution.getIdcaution()).isEqualTo(DEFAULT_IDCAUTION);
        assertThat(testCaution.getMontantCaution()).isEqualTo(DEFAULT_MONTANT_CAUTION);
        assertThat(testCaution.getObjet()).isEqualTo(DEFAULT_OBJET);
        assertThat(testCaution.getNumeroCaution()).isEqualTo(DEFAULT_NUMERO_CAUTION);
        assertThat(testCaution.getDateDemande()).isEqualTo(DEFAULT_DATE_DEMANDE);
        assertThat(testCaution.getDateRetrait()).isEqualTo(DEFAULT_DATE_RETRAIT);
        assertThat(testCaution.getDateDepot()).isEqualTo(DEFAULT_DATE_DEPOT);
        assertThat(testCaution.getNumeroMarche()).isEqualTo(DEFAULT_NUMERO_MARCHE);
        assertThat(testCaution.getStatusCaution()).isEqualTo(DEFAULT_STATUS_CAUTION);
        assertThat(testCaution.getTypeCaution()).isEqualTo(DEFAULT_TYPE_CAUTION);
    }

    @Test
    @Transactional
    public void createCautionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cautionRepository.findAll().size();

        // Create the Caution with an existing ID
        caution.setId(1L);
        CautionDTO cautionDTO = cautionMapper.toDto(caution);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCautionMockMvc.perform(post("/api/cautions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cautionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Caution in the database
        List<Caution> cautionList = cautionRepository.findAll();
        assertThat(cautionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdcautionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cautionRepository.findAll().size();
        // set the field null
        caution.setIdcaution(null);

        // Create the Caution, which fails.
        CautionDTO cautionDTO = cautionMapper.toDto(caution);

        restCautionMockMvc.perform(post("/api/cautions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cautionDTO)))
            .andExpect(status().isBadRequest());

        List<Caution> cautionList = cautionRepository.findAll();
        assertThat(cautionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCautions() throws Exception {
        // Initialize the database
        cautionRepository.saveAndFlush(caution);

        // Get all the cautionList
        restCautionMockMvc.perform(get("/api/cautions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caution.getId().intValue())))
            .andExpect(jsonPath("$.[*].idcaution").value(hasItem(DEFAULT_IDCAUTION.intValue())))
            .andExpect(jsonPath("$.[*].montantCaution").value(hasItem(DEFAULT_MONTANT_CAUTION.doubleValue())))
            .andExpect(jsonPath("$.[*].objet").value(hasItem(DEFAULT_OBJET)))
            .andExpect(jsonPath("$.[*].numeroCaution").value(hasItem(DEFAULT_NUMERO_CAUTION)))
            .andExpect(jsonPath("$.[*].dateDemande").value(hasItem(DEFAULT_DATE_DEMANDE.toString())))
            .andExpect(jsonPath("$.[*].dateRetrait").value(hasItem(DEFAULT_DATE_RETRAIT.toString())))
            .andExpect(jsonPath("$.[*].dateDepot").value(hasItem(DEFAULT_DATE_DEPOT.toString())))
            .andExpect(jsonPath("$.[*].numeroMarche").value(hasItem(DEFAULT_NUMERO_MARCHE)))
            .andExpect(jsonPath("$.[*].statusCaution").value(hasItem(DEFAULT_STATUS_CAUTION.toString())))
            .andExpect(jsonPath("$.[*].typeCaution").value(hasItem(DEFAULT_TYPE_CAUTION.toString())));
    }
    
    @Test
    @Transactional
    public void getCaution() throws Exception {
        // Initialize the database
        cautionRepository.saveAndFlush(caution);

        // Get the caution
        restCautionMockMvc.perform(get("/api/cautions/{id}", caution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(caution.getId().intValue()))
            .andExpect(jsonPath("$.idcaution").value(DEFAULT_IDCAUTION.intValue()))
            .andExpect(jsonPath("$.montantCaution").value(DEFAULT_MONTANT_CAUTION.doubleValue()))
            .andExpect(jsonPath("$.objet").value(DEFAULT_OBJET))
            .andExpect(jsonPath("$.numeroCaution").value(DEFAULT_NUMERO_CAUTION))
            .andExpect(jsonPath("$.dateDemande").value(DEFAULT_DATE_DEMANDE.toString()))
            .andExpect(jsonPath("$.dateRetrait").value(DEFAULT_DATE_RETRAIT.toString()))
            .andExpect(jsonPath("$.dateDepot").value(DEFAULT_DATE_DEPOT.toString()))
            .andExpect(jsonPath("$.numeroMarche").value(DEFAULT_NUMERO_MARCHE))
            .andExpect(jsonPath("$.statusCaution").value(DEFAULT_STATUS_CAUTION.toString()))
            .andExpect(jsonPath("$.typeCaution").value(DEFAULT_TYPE_CAUTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCaution() throws Exception {
        // Get the caution
        restCautionMockMvc.perform(get("/api/cautions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaution() throws Exception {
        // Initialize the database
        cautionRepository.saveAndFlush(caution);

        int databaseSizeBeforeUpdate = cautionRepository.findAll().size();

        // Update the caution
        Caution updatedCaution = cautionRepository.findById(caution.getId()).get();
        // Disconnect from session so that the updates on updatedCaution are not directly saved in db
        em.detach(updatedCaution);
        updatedCaution
            .idcaution(UPDATED_IDCAUTION)
            .montantCaution(UPDATED_MONTANT_CAUTION)
            .objet(UPDATED_OBJET)
            .numeroCaution(UPDATED_NUMERO_CAUTION)
            .dateDemande(UPDATED_DATE_DEMANDE)
            .dateRetrait(UPDATED_DATE_RETRAIT)
            .dateDepot(UPDATED_DATE_DEPOT)
            .numeroMarche(UPDATED_NUMERO_MARCHE)
            .statusCaution(UPDATED_STATUS_CAUTION)
            .typeCaution(UPDATED_TYPE_CAUTION);
        CautionDTO cautionDTO = cautionMapper.toDto(updatedCaution);

        restCautionMockMvc.perform(put("/api/cautions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cautionDTO)))
            .andExpect(status().isOk());

        // Validate the Caution in the database
        List<Caution> cautionList = cautionRepository.findAll();
        assertThat(cautionList).hasSize(databaseSizeBeforeUpdate);
        Caution testCaution = cautionList.get(cautionList.size() - 1);
        assertThat(testCaution.getIdcaution()).isEqualTo(UPDATED_IDCAUTION);
        assertThat(testCaution.getMontantCaution()).isEqualTo(UPDATED_MONTANT_CAUTION);
        assertThat(testCaution.getObjet()).isEqualTo(UPDATED_OBJET);
        assertThat(testCaution.getNumeroCaution()).isEqualTo(UPDATED_NUMERO_CAUTION);
        assertThat(testCaution.getDateDemande()).isEqualTo(UPDATED_DATE_DEMANDE);
        assertThat(testCaution.getDateRetrait()).isEqualTo(UPDATED_DATE_RETRAIT);
        assertThat(testCaution.getDateDepot()).isEqualTo(UPDATED_DATE_DEPOT);
        assertThat(testCaution.getNumeroMarche()).isEqualTo(UPDATED_NUMERO_MARCHE);
        assertThat(testCaution.getStatusCaution()).isEqualTo(UPDATED_STATUS_CAUTION);
        assertThat(testCaution.getTypeCaution()).isEqualTo(UPDATED_TYPE_CAUTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCaution() throws Exception {
        int databaseSizeBeforeUpdate = cautionRepository.findAll().size();

        // Create the Caution
        CautionDTO cautionDTO = cautionMapper.toDto(caution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCautionMockMvc.perform(put("/api/cautions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cautionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Caution in the database
        List<Caution> cautionList = cautionRepository.findAll();
        assertThat(cautionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaution() throws Exception {
        // Initialize the database
        cautionRepository.saveAndFlush(caution);

        int databaseSizeBeforeDelete = cautionRepository.findAll().size();

        // Delete the caution
        restCautionMockMvc.perform(delete("/api/cautions/{id}", caution.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Caution> cautionList = cautionRepository.findAll();
        assertThat(cautionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
