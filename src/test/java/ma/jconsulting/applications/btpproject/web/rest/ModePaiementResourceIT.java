package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.ModePaiement;
import ma.jconsulting.applications.btpproject.repository.ModePaiementRepository;
import ma.jconsulting.applications.btpproject.service.ModePaiementService;
import ma.jconsulting.applications.btpproject.service.dto.ModePaiementDTO;
import ma.jconsulting.applications.btpproject.service.mapper.ModePaiementMapper;
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
import java.util.List;

import static ma.jconsulting.applications.btpproject.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ModePaiementResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class ModePaiementResourceIT {

    private static final Long DEFAULT_ID_MODE_PAIEMENT = 1L;
    private static final Long UPDATED_ID_MODE_PAIEMENT = 2L;

    private static final String DEFAULT_MODE_PAIEMENT = "AAAAAAAAAA";
    private static final String UPDATED_MODE_PAIEMENT = "BBBBBBBBBB";

    @Autowired
    private ModePaiementRepository modePaiementRepository;

    @Autowired
    private ModePaiementMapper modePaiementMapper;

    @Autowired
    private ModePaiementService modePaiementService;

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

    private MockMvc restModePaiementMockMvc;

    private ModePaiement modePaiement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModePaiementResource modePaiementResource = new ModePaiementResource(modePaiementService);
        this.restModePaiementMockMvc = MockMvcBuilders.standaloneSetup(modePaiementResource)
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
    public static ModePaiement createEntity(EntityManager em) {
        ModePaiement modePaiement = new ModePaiement()
            .idModePaiement(DEFAULT_ID_MODE_PAIEMENT)
            .modePaiement(DEFAULT_MODE_PAIEMENT);
        return modePaiement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModePaiement createUpdatedEntity(EntityManager em) {
        ModePaiement modePaiement = new ModePaiement()
            .idModePaiement(UPDATED_ID_MODE_PAIEMENT)
            .modePaiement(UPDATED_MODE_PAIEMENT);
        return modePaiement;
    }

    @BeforeEach
    public void initTest() {
        modePaiement = createEntity(em);
    }

    @Test
    @Transactional
    public void createModePaiement() throws Exception {
        int databaseSizeBeforeCreate = modePaiementRepository.findAll().size();

        // Create the ModePaiement
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);
        restModePaiementMockMvc.perform(post("/api/mode-paiements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isCreated());

        // Validate the ModePaiement in the database
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeCreate + 1);
        ModePaiement testModePaiement = modePaiementList.get(modePaiementList.size() - 1);
        assertThat(testModePaiement.getIdModePaiement()).isEqualTo(DEFAULT_ID_MODE_PAIEMENT);
        assertThat(testModePaiement.getModePaiement()).isEqualTo(DEFAULT_MODE_PAIEMENT);
    }

    @Test
    @Transactional
    public void createModePaiementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modePaiementRepository.findAll().size();

        // Create the ModePaiement with an existing ID
        modePaiement.setId(1L);
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModePaiementMockMvc.perform(post("/api/mode-paiements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModePaiement in the database
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdModePaiementIsRequired() throws Exception {
        int databaseSizeBeforeTest = modePaiementRepository.findAll().size();
        // set the field null
        modePaiement.setIdModePaiement(null);

        // Create the ModePaiement, which fails.
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);

        restModePaiementMockMvc.perform(post("/api/mode-paiements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isBadRequest());

        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModePaiements() throws Exception {
        // Initialize the database
        modePaiementRepository.saveAndFlush(modePaiement);

        // Get all the modePaiementList
        restModePaiementMockMvc.perform(get("/api/mode-paiements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modePaiement.getId().intValue())))
            .andExpect(jsonPath("$.[*].idModePaiement").value(hasItem(DEFAULT_ID_MODE_PAIEMENT.intValue())))
            .andExpect(jsonPath("$.[*].modePaiement").value(hasItem(DEFAULT_MODE_PAIEMENT)));
    }
    
    @Test
    @Transactional
    public void getModePaiement() throws Exception {
        // Initialize the database
        modePaiementRepository.saveAndFlush(modePaiement);

        // Get the modePaiement
        restModePaiementMockMvc.perform(get("/api/mode-paiements/{id}", modePaiement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modePaiement.getId().intValue()))
            .andExpect(jsonPath("$.idModePaiement").value(DEFAULT_ID_MODE_PAIEMENT.intValue()))
            .andExpect(jsonPath("$.modePaiement").value(DEFAULT_MODE_PAIEMENT));
    }

    @Test
    @Transactional
    public void getNonExistingModePaiement() throws Exception {
        // Get the modePaiement
        restModePaiementMockMvc.perform(get("/api/mode-paiements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModePaiement() throws Exception {
        // Initialize the database
        modePaiementRepository.saveAndFlush(modePaiement);

        int databaseSizeBeforeUpdate = modePaiementRepository.findAll().size();

        // Update the modePaiement
        ModePaiement updatedModePaiement = modePaiementRepository.findById(modePaiement.getId()).get();
        // Disconnect from session so that the updates on updatedModePaiement are not directly saved in db
        em.detach(updatedModePaiement);
        updatedModePaiement
            .idModePaiement(UPDATED_ID_MODE_PAIEMENT)
            .modePaiement(UPDATED_MODE_PAIEMENT);
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(updatedModePaiement);

        restModePaiementMockMvc.perform(put("/api/mode-paiements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isOk());

        // Validate the ModePaiement in the database
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeUpdate);
        ModePaiement testModePaiement = modePaiementList.get(modePaiementList.size() - 1);
        assertThat(testModePaiement.getIdModePaiement()).isEqualTo(UPDATED_ID_MODE_PAIEMENT);
        assertThat(testModePaiement.getModePaiement()).isEqualTo(UPDATED_MODE_PAIEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingModePaiement() throws Exception {
        int databaseSizeBeforeUpdate = modePaiementRepository.findAll().size();

        // Create the ModePaiement
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModePaiementMockMvc.perform(put("/api/mode-paiements")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModePaiement in the database
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModePaiement() throws Exception {
        // Initialize the database
        modePaiementRepository.saveAndFlush(modePaiement);

        int databaseSizeBeforeDelete = modePaiementRepository.findAll().size();

        // Delete the modePaiement
        restModePaiementMockMvc.perform(delete("/api/mode-paiements/{id}", modePaiement.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
