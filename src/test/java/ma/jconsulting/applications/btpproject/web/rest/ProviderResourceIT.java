package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.Provider;
import ma.jconsulting.applications.btpproject.repository.ProviderRepository;
import ma.jconsulting.applications.btpproject.service.ProviderService;
import ma.jconsulting.applications.btpproject.service.dto.ProviderDTO;
import ma.jconsulting.applications.btpproject.service.mapper.ProviderMapper;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static ma.jconsulting.applications.btpproject.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProviderResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class ProviderResourceIT {

    private static final Long DEFAULT_ID_PROVIDER = 1L;
    private static final Long UPDATED_ID_PROVIDER = 2L;

    private static final String DEFAULT_PROVIDER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PROVIDER_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROVIDER_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PROVIDER_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROVIDER_LOGO_CONTENT_TYPE = "image/png";

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private ProviderService providerService;

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

    private MockMvc restProviderMockMvc;

    private Provider provider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProviderResource providerResource = new ProviderResource(providerService);
        this.restProviderMockMvc = MockMvcBuilders.standaloneSetup(providerResource)
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
    public static Provider createEntity(EntityManager em) {
        Provider provider = new Provider()
            .idProvider(DEFAULT_ID_PROVIDER)
            .providerName(DEFAULT_PROVIDER_NAME)
            .providerLogo(DEFAULT_PROVIDER_LOGO)
            .providerLogoContentType(DEFAULT_PROVIDER_LOGO_CONTENT_TYPE);
        return provider;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Provider createUpdatedEntity(EntityManager em) {
        Provider provider = new Provider()
            .idProvider(UPDATED_ID_PROVIDER)
            .providerName(UPDATED_PROVIDER_NAME)
            .providerLogo(UPDATED_PROVIDER_LOGO)
            .providerLogoContentType(UPDATED_PROVIDER_LOGO_CONTENT_TYPE);
        return provider;
    }

    @BeforeEach
    public void initTest() {
        provider = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvider() throws Exception {
        int databaseSizeBeforeCreate = providerRepository.findAll().size();

        // Create the Provider
        ProviderDTO providerDTO = providerMapper.toDto(provider);
        restProviderMockMvc.perform(post("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isCreated());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeCreate + 1);
        Provider testProvider = providerList.get(providerList.size() - 1);
        assertThat(testProvider.getIdProvider()).isEqualTo(DEFAULT_ID_PROVIDER);
        assertThat(testProvider.getProviderName()).isEqualTo(DEFAULT_PROVIDER_NAME);
        assertThat(testProvider.getProviderLogo()).isEqualTo(DEFAULT_PROVIDER_LOGO);
        assertThat(testProvider.getProviderLogoContentType()).isEqualTo(DEFAULT_PROVIDER_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createProviderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = providerRepository.findAll().size();

        // Create the Provider with an existing ID
        provider.setId(1L);
        ProviderDTO providerDTO = providerMapper.toDto(provider);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProviderMockMvc.perform(post("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdProviderIsRequired() throws Exception {
        int databaseSizeBeforeTest = providerRepository.findAll().size();
        // set the field null
        provider.setIdProvider(null);

        // Create the Provider, which fails.
        ProviderDTO providerDTO = providerMapper.toDto(provider);

        restProviderMockMvc.perform(post("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProviders() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get all the providerList
        restProviderMockMvc.perform(get("/api/providers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provider.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProvider").value(hasItem(DEFAULT_ID_PROVIDER.intValue())))
            .andExpect(jsonPath("$.[*].providerName").value(hasItem(DEFAULT_PROVIDER_NAME)))
            .andExpect(jsonPath("$.[*].providerLogoContentType").value(hasItem(DEFAULT_PROVIDER_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].providerLogo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROVIDER_LOGO))));
    }
    
    @Test
    @Transactional
    public void getProvider() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        // Get the provider
        restProviderMockMvc.perform(get("/api/providers/{id}", provider.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(provider.getId().intValue()))
            .andExpect(jsonPath("$.idProvider").value(DEFAULT_ID_PROVIDER.intValue()))
            .andExpect(jsonPath("$.providerName").value(DEFAULT_PROVIDER_NAME))
            .andExpect(jsonPath("$.providerLogoContentType").value(DEFAULT_PROVIDER_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.providerLogo").value(Base64Utils.encodeToString(DEFAULT_PROVIDER_LOGO)));
    }

    @Test
    @Transactional
    public void getNonExistingProvider() throws Exception {
        // Get the provider
        restProviderMockMvc.perform(get("/api/providers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvider() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        int databaseSizeBeforeUpdate = providerRepository.findAll().size();

        // Update the provider
        Provider updatedProvider = providerRepository.findById(provider.getId()).get();
        // Disconnect from session so that the updates on updatedProvider are not directly saved in db
        em.detach(updatedProvider);
        updatedProvider
            .idProvider(UPDATED_ID_PROVIDER)
            .providerName(UPDATED_PROVIDER_NAME)
            .providerLogo(UPDATED_PROVIDER_LOGO)
            .providerLogoContentType(UPDATED_PROVIDER_LOGO_CONTENT_TYPE);
        ProviderDTO providerDTO = providerMapper.toDto(updatedProvider);

        restProviderMockMvc.perform(put("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isOk());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeUpdate);
        Provider testProvider = providerList.get(providerList.size() - 1);
        assertThat(testProvider.getIdProvider()).isEqualTo(UPDATED_ID_PROVIDER);
        assertThat(testProvider.getProviderName()).isEqualTo(UPDATED_PROVIDER_NAME);
        assertThat(testProvider.getProviderLogo()).isEqualTo(UPDATED_PROVIDER_LOGO);
        assertThat(testProvider.getProviderLogoContentType()).isEqualTo(UPDATED_PROVIDER_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingProvider() throws Exception {
        int databaseSizeBeforeUpdate = providerRepository.findAll().size();

        // Create the Provider
        ProviderDTO providerDTO = providerMapper.toDto(provider);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProviderMockMvc.perform(put("/api/providers")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Provider in the database
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvider() throws Exception {
        // Initialize the database
        providerRepository.saveAndFlush(provider);

        int databaseSizeBeforeDelete = providerRepository.findAll().size();

        // Delete the provider
        restProviderMockMvc.perform(delete("/api/providers/{id}", provider.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Provider> providerList = providerRepository.findAll();
        assertThat(providerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
