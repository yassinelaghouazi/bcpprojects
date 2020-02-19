package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.MaitreOuvrage;
import ma.jconsulting.applications.btpproject.repository.MaitreOuvrageRepository;
import ma.jconsulting.applications.btpproject.service.MaitreOuvrageService;
import ma.jconsulting.applications.btpproject.service.dto.MaitreOuvrageDTO;
import ma.jconsulting.applications.btpproject.service.mapper.MaitreOuvrageMapper;
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
 * Integration tests for the {@link MaitreOuvrageResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class MaitreOuvrageResourceIT {

    private static final Long DEFAULT_ID_MAITRE_OUVRAGE = 1L;
    private static final Long UPDATED_ID_MAITRE_OUVRAGE = 2L;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PERSONNE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PERSONNE = "BBBBBBBBBB";

    @Autowired
    private MaitreOuvrageRepository maitreOuvrageRepository;

    @Autowired
    private MaitreOuvrageMapper maitreOuvrageMapper;

    @Autowired
    private MaitreOuvrageService maitreOuvrageService;

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

    private MockMvc restMaitreOuvrageMockMvc;

    private MaitreOuvrage maitreOuvrage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MaitreOuvrageResource maitreOuvrageResource = new MaitreOuvrageResource(maitreOuvrageService);
        this.restMaitreOuvrageMockMvc = MockMvcBuilders.standaloneSetup(maitreOuvrageResource)
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
    public static MaitreOuvrage createEntity(EntityManager em) {
        MaitreOuvrage maitreOuvrage = new MaitreOuvrage()
            .idMaitreOuvrage(DEFAULT_ID_MAITRE_OUVRAGE)
            .nom(DEFAULT_NOM)
            .email(DEFAULT_EMAIL)
            .tel(DEFAULT_TEL)
            .contactPersonne(DEFAULT_CONTACT_PERSONNE);
        return maitreOuvrage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MaitreOuvrage createUpdatedEntity(EntityManager em) {
        MaitreOuvrage maitreOuvrage = new MaitreOuvrage()
            .idMaitreOuvrage(UPDATED_ID_MAITRE_OUVRAGE)
            .nom(UPDATED_NOM)
            .email(UPDATED_EMAIL)
            .tel(UPDATED_TEL)
            .contactPersonne(UPDATED_CONTACT_PERSONNE);
        return maitreOuvrage;
    }

    @BeforeEach
    public void initTest() {
        maitreOuvrage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaitreOuvrage() throws Exception {
        int databaseSizeBeforeCreate = maitreOuvrageRepository.findAll().size();

        // Create the MaitreOuvrage
        MaitreOuvrageDTO maitreOuvrageDTO = maitreOuvrageMapper.toDto(maitreOuvrage);
        restMaitreOuvrageMockMvc.perform(post("/api/maitre-ouvrages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maitreOuvrageDTO)))
            .andExpect(status().isCreated());

        // Validate the MaitreOuvrage in the database
        List<MaitreOuvrage> maitreOuvrageList = maitreOuvrageRepository.findAll();
        assertThat(maitreOuvrageList).hasSize(databaseSizeBeforeCreate + 1);
        MaitreOuvrage testMaitreOuvrage = maitreOuvrageList.get(maitreOuvrageList.size() - 1);
        assertThat(testMaitreOuvrage.getIdMaitreOuvrage()).isEqualTo(DEFAULT_ID_MAITRE_OUVRAGE);
        assertThat(testMaitreOuvrage.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMaitreOuvrage.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMaitreOuvrage.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testMaitreOuvrage.getContactPersonne()).isEqualTo(DEFAULT_CONTACT_PERSONNE);
    }

    @Test
    @Transactional
    public void createMaitreOuvrageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maitreOuvrageRepository.findAll().size();

        // Create the MaitreOuvrage with an existing ID
        maitreOuvrage.setId(1L);
        MaitreOuvrageDTO maitreOuvrageDTO = maitreOuvrageMapper.toDto(maitreOuvrage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaitreOuvrageMockMvc.perform(post("/api/maitre-ouvrages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maitreOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MaitreOuvrage in the database
        List<MaitreOuvrage> maitreOuvrageList = maitreOuvrageRepository.findAll();
        assertThat(maitreOuvrageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMaitreOuvrages() throws Exception {
        // Initialize the database
        maitreOuvrageRepository.saveAndFlush(maitreOuvrage);

        // Get all the maitreOuvrageList
        restMaitreOuvrageMockMvc.perform(get("/api/maitre-ouvrages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maitreOuvrage.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMaitreOuvrage").value(hasItem(DEFAULT_ID_MAITRE_OUVRAGE.intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].contactPersonne").value(hasItem(DEFAULT_CONTACT_PERSONNE)));
    }
    
    @Test
    @Transactional
    public void getMaitreOuvrage() throws Exception {
        // Initialize the database
        maitreOuvrageRepository.saveAndFlush(maitreOuvrage);

        // Get the maitreOuvrage
        restMaitreOuvrageMockMvc.perform(get("/api/maitre-ouvrages/{id}", maitreOuvrage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(maitreOuvrage.getId().intValue()))
            .andExpect(jsonPath("$.idMaitreOuvrage").value(DEFAULT_ID_MAITRE_OUVRAGE.intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.contactPersonne").value(DEFAULT_CONTACT_PERSONNE));
    }

    @Test
    @Transactional
    public void getNonExistingMaitreOuvrage() throws Exception {
        // Get the maitreOuvrage
        restMaitreOuvrageMockMvc.perform(get("/api/maitre-ouvrages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaitreOuvrage() throws Exception {
        // Initialize the database
        maitreOuvrageRepository.saveAndFlush(maitreOuvrage);

        int databaseSizeBeforeUpdate = maitreOuvrageRepository.findAll().size();

        // Update the maitreOuvrage
        MaitreOuvrage updatedMaitreOuvrage = maitreOuvrageRepository.findById(maitreOuvrage.getId()).get();
        // Disconnect from session so that the updates on updatedMaitreOuvrage are not directly saved in db
        em.detach(updatedMaitreOuvrage);
        updatedMaitreOuvrage
            .idMaitreOuvrage(UPDATED_ID_MAITRE_OUVRAGE)
            .nom(UPDATED_NOM)
            .email(UPDATED_EMAIL)
            .tel(UPDATED_TEL)
            .contactPersonne(UPDATED_CONTACT_PERSONNE);
        MaitreOuvrageDTO maitreOuvrageDTO = maitreOuvrageMapper.toDto(updatedMaitreOuvrage);

        restMaitreOuvrageMockMvc.perform(put("/api/maitre-ouvrages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maitreOuvrageDTO)))
            .andExpect(status().isOk());

        // Validate the MaitreOuvrage in the database
        List<MaitreOuvrage> maitreOuvrageList = maitreOuvrageRepository.findAll();
        assertThat(maitreOuvrageList).hasSize(databaseSizeBeforeUpdate);
        MaitreOuvrage testMaitreOuvrage = maitreOuvrageList.get(maitreOuvrageList.size() - 1);
        assertThat(testMaitreOuvrage.getIdMaitreOuvrage()).isEqualTo(UPDATED_ID_MAITRE_OUVRAGE);
        assertThat(testMaitreOuvrage.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMaitreOuvrage.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMaitreOuvrage.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testMaitreOuvrage.getContactPersonne()).isEqualTo(UPDATED_CONTACT_PERSONNE);
    }

    @Test
    @Transactional
    public void updateNonExistingMaitreOuvrage() throws Exception {
        int databaseSizeBeforeUpdate = maitreOuvrageRepository.findAll().size();

        // Create the MaitreOuvrage
        MaitreOuvrageDTO maitreOuvrageDTO = maitreOuvrageMapper.toDto(maitreOuvrage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaitreOuvrageMockMvc.perform(put("/api/maitre-ouvrages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maitreOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MaitreOuvrage in the database
        List<MaitreOuvrage> maitreOuvrageList = maitreOuvrageRepository.findAll();
        assertThat(maitreOuvrageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMaitreOuvrage() throws Exception {
        // Initialize the database
        maitreOuvrageRepository.saveAndFlush(maitreOuvrage);

        int databaseSizeBeforeDelete = maitreOuvrageRepository.findAll().size();

        // Delete the maitreOuvrage
        restMaitreOuvrageMockMvc.perform(delete("/api/maitre-ouvrages/{id}", maitreOuvrage.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MaitreOuvrage> maitreOuvrageList = maitreOuvrageRepository.findAll();
        assertThat(maitreOuvrageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
