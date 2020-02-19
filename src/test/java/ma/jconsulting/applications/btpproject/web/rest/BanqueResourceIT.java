package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.Banque;
import ma.jconsulting.applications.btpproject.repository.BanqueRepository;
import ma.jconsulting.applications.btpproject.service.BanqueService;
import ma.jconsulting.applications.btpproject.service.dto.BanqueDTO;
import ma.jconsulting.applications.btpproject.service.mapper.BanqueMapper;
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
 * Integration tests for the {@link BanqueResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class BanqueResourceIT {

    private static final Long DEFAULT_ID_BANQUE = 1L;
    private static final Long UPDATED_ID_BANQUE = 2L;

    private static final String DEFAULT_BANQUE = "AAAAAAAAAA";
    private static final String UPDATED_BANQUE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_AGENCE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_AGENCE = "BBBBBBBBBB";

    @Autowired
    private BanqueRepository banqueRepository;

    @Autowired
    private BanqueMapper banqueMapper;

    @Autowired
    private BanqueService banqueService;

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

    private MockMvc restBanqueMockMvc;

    private Banque banque;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BanqueResource banqueResource = new BanqueResource(banqueService);
        this.restBanqueMockMvc = MockMvcBuilders.standaloneSetup(banqueResource)
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
    public static Banque createEntity(EntityManager em) {
        Banque banque = new Banque()
            .idBanque(DEFAULT_ID_BANQUE)
            .banque(DEFAULT_BANQUE)
            .contactEmail(DEFAULT_CONTACT_EMAIL)
            .contactTel(DEFAULT_CONTACT_TEL)
            .adresseAgence(DEFAULT_ADRESSE_AGENCE);
        return banque;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Banque createUpdatedEntity(EntityManager em) {
        Banque banque = new Banque()
            .idBanque(UPDATED_ID_BANQUE)
            .banque(UPDATED_BANQUE)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .contactTel(UPDATED_CONTACT_TEL)
            .adresseAgence(UPDATED_ADRESSE_AGENCE);
        return banque;
    }

    @BeforeEach
    public void initTest() {
        banque = createEntity(em);
    }

    @Test
    @Transactional
    public void createBanque() throws Exception {
        int databaseSizeBeforeCreate = banqueRepository.findAll().size();

        // Create the Banque
        BanqueDTO banqueDTO = banqueMapper.toDto(banque);
        restBanqueMockMvc.perform(post("/api/banques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueDTO)))
            .andExpect(status().isCreated());

        // Validate the Banque in the database
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeCreate + 1);
        Banque testBanque = banqueList.get(banqueList.size() - 1);
        assertThat(testBanque.getIdBanque()).isEqualTo(DEFAULT_ID_BANQUE);
        assertThat(testBanque.getBanque()).isEqualTo(DEFAULT_BANQUE);
        assertThat(testBanque.getContactEmail()).isEqualTo(DEFAULT_CONTACT_EMAIL);
        assertThat(testBanque.getContactTel()).isEqualTo(DEFAULT_CONTACT_TEL);
        assertThat(testBanque.getAdresseAgence()).isEqualTo(DEFAULT_ADRESSE_AGENCE);
    }

    @Test
    @Transactional
    public void createBanqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = banqueRepository.findAll().size();

        // Create the Banque with an existing ID
        banque.setId(1L);
        BanqueDTO banqueDTO = banqueMapper.toDto(banque);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBanqueMockMvc.perform(post("/api/banques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Banque in the database
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdBanqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = banqueRepository.findAll().size();
        // set the field null
        banque.setIdBanque(null);

        // Create the Banque, which fails.
        BanqueDTO banqueDTO = banqueMapper.toDto(banque);

        restBanqueMockMvc.perform(post("/api/banques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueDTO)))
            .andExpect(status().isBadRequest());

        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBanqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = banqueRepository.findAll().size();
        // set the field null
        banque.setBanque(null);

        // Create the Banque, which fails.
        BanqueDTO banqueDTO = banqueMapper.toDto(banque);

        restBanqueMockMvc.perform(post("/api/banques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueDTO)))
            .andExpect(status().isBadRequest());

        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBanques() throws Exception {
        // Initialize the database
        banqueRepository.saveAndFlush(banque);

        // Get all the banqueList
        restBanqueMockMvc.perform(get("/api/banques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(banque.getId().intValue())))
            .andExpect(jsonPath("$.[*].idBanque").value(hasItem(DEFAULT_ID_BANQUE.intValue())))
            .andExpect(jsonPath("$.[*].banque").value(hasItem(DEFAULT_BANQUE)))
            .andExpect(jsonPath("$.[*].contactEmail").value(hasItem(DEFAULT_CONTACT_EMAIL)))
            .andExpect(jsonPath("$.[*].contactTel").value(hasItem(DEFAULT_CONTACT_TEL)))
            .andExpect(jsonPath("$.[*].adresseAgence").value(hasItem(DEFAULT_ADRESSE_AGENCE)));
    }
    
    @Test
    @Transactional
    public void getBanque() throws Exception {
        // Initialize the database
        banqueRepository.saveAndFlush(banque);

        // Get the banque
        restBanqueMockMvc.perform(get("/api/banques/{id}", banque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(banque.getId().intValue()))
            .andExpect(jsonPath("$.idBanque").value(DEFAULT_ID_BANQUE.intValue()))
            .andExpect(jsonPath("$.banque").value(DEFAULT_BANQUE))
            .andExpect(jsonPath("$.contactEmail").value(DEFAULT_CONTACT_EMAIL))
            .andExpect(jsonPath("$.contactTel").value(DEFAULT_CONTACT_TEL))
            .andExpect(jsonPath("$.adresseAgence").value(DEFAULT_ADRESSE_AGENCE));
    }

    @Test
    @Transactional
    public void getNonExistingBanque() throws Exception {
        // Get the banque
        restBanqueMockMvc.perform(get("/api/banques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBanque() throws Exception {
        // Initialize the database
        banqueRepository.saveAndFlush(banque);

        int databaseSizeBeforeUpdate = banqueRepository.findAll().size();

        // Update the banque
        Banque updatedBanque = banqueRepository.findById(banque.getId()).get();
        // Disconnect from session so that the updates on updatedBanque are not directly saved in db
        em.detach(updatedBanque);
        updatedBanque
            .idBanque(UPDATED_ID_BANQUE)
            .banque(UPDATED_BANQUE)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .contactTel(UPDATED_CONTACT_TEL)
            .adresseAgence(UPDATED_ADRESSE_AGENCE);
        BanqueDTO banqueDTO = banqueMapper.toDto(updatedBanque);

        restBanqueMockMvc.perform(put("/api/banques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueDTO)))
            .andExpect(status().isOk());

        // Validate the Banque in the database
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeUpdate);
        Banque testBanque = banqueList.get(banqueList.size() - 1);
        assertThat(testBanque.getIdBanque()).isEqualTo(UPDATED_ID_BANQUE);
        assertThat(testBanque.getBanque()).isEqualTo(UPDATED_BANQUE);
        assertThat(testBanque.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testBanque.getContactTel()).isEqualTo(UPDATED_CONTACT_TEL);
        assertThat(testBanque.getAdresseAgence()).isEqualTo(UPDATED_ADRESSE_AGENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingBanque() throws Exception {
        int databaseSizeBeforeUpdate = banqueRepository.findAll().size();

        // Create the Banque
        BanqueDTO banqueDTO = banqueMapper.toDto(banque);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBanqueMockMvc.perform(put("/api/banques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Banque in the database
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBanque() throws Exception {
        // Initialize the database
        banqueRepository.saveAndFlush(banque);

        int databaseSizeBeforeDelete = banqueRepository.findAll().size();

        // Delete the banque
        restBanqueMockMvc.perform(delete("/api/banques/{id}", banque.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
