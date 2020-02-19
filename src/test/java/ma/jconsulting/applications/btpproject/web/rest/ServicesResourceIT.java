package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.Services;
import ma.jconsulting.applications.btpproject.repository.ServicesRepository;
import ma.jconsulting.applications.btpproject.service.ServicesService;
import ma.jconsulting.applications.btpproject.service.dto.ServicesDTO;
import ma.jconsulting.applications.btpproject.service.mapper.ServicesMapper;
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
 * Integration tests for the {@link ServicesResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class ServicesResourceIT {

    private static final Long DEFAULT_ID_SERVICES = 1L;
    private static final Long UPDATED_ID_SERVICES = 2L;

    private static final String DEFAULT_SERVICES_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SERVICES_DESC = "BBBBBBBBBB";

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ServicesMapper servicesMapper;

    @Autowired
    private ServicesService servicesService;

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

    private MockMvc restServicesMockMvc;

    private Services services;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServicesResource servicesResource = new ServicesResource(servicesService);
        this.restServicesMockMvc = MockMvcBuilders.standaloneSetup(servicesResource)
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
    public static Services createEntity(EntityManager em) {
        Services services = new Services()
            .idServices(DEFAULT_ID_SERVICES)
            .servicesDesc(DEFAULT_SERVICES_DESC);
        return services;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Services createUpdatedEntity(EntityManager em) {
        Services services = new Services()
            .idServices(UPDATED_ID_SERVICES)
            .servicesDesc(UPDATED_SERVICES_DESC);
        return services;
    }

    @BeforeEach
    public void initTest() {
        services = createEntity(em);
    }

    @Test
    @Transactional
    public void createServices() throws Exception {
        int databaseSizeBeforeCreate = servicesRepository.findAll().size();

        // Create the Services
        ServicesDTO servicesDTO = servicesMapper.toDto(services);
        restServicesMockMvc.perform(post("/api/services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(servicesDTO)))
            .andExpect(status().isCreated());

        // Validate the Services in the database
        List<Services> servicesList = servicesRepository.findAll();
        assertThat(servicesList).hasSize(databaseSizeBeforeCreate + 1);
        Services testServices = servicesList.get(servicesList.size() - 1);
        assertThat(testServices.getIdServices()).isEqualTo(DEFAULT_ID_SERVICES);
        assertThat(testServices.getServicesDesc()).isEqualTo(DEFAULT_SERVICES_DESC);
    }

    @Test
    @Transactional
    public void createServicesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = servicesRepository.findAll().size();

        // Create the Services with an existing ID
        services.setId(1L);
        ServicesDTO servicesDTO = servicesMapper.toDto(services);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServicesMockMvc.perform(post("/api/services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(servicesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Services in the database
        List<Services> servicesList = servicesRepository.findAll();
        assertThat(servicesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdServicesIsRequired() throws Exception {
        int databaseSizeBeforeTest = servicesRepository.findAll().size();
        // set the field null
        services.setIdServices(null);

        // Create the Services, which fails.
        ServicesDTO servicesDTO = servicesMapper.toDto(services);

        restServicesMockMvc.perform(post("/api/services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(servicesDTO)))
            .andExpect(status().isBadRequest());

        List<Services> servicesList = servicesRepository.findAll();
        assertThat(servicesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllServices() throws Exception {
        // Initialize the database
        servicesRepository.saveAndFlush(services);

        // Get all the servicesList
        restServicesMockMvc.perform(get("/api/services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(services.getId().intValue())))
            .andExpect(jsonPath("$.[*].idServices").value(hasItem(DEFAULT_ID_SERVICES.intValue())))
            .andExpect(jsonPath("$.[*].servicesDesc").value(hasItem(DEFAULT_SERVICES_DESC)));
    }
    
    @Test
    @Transactional
    public void getServices() throws Exception {
        // Initialize the database
        servicesRepository.saveAndFlush(services);

        // Get the services
        restServicesMockMvc.perform(get("/api/services/{id}", services.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(services.getId().intValue()))
            .andExpect(jsonPath("$.idServices").value(DEFAULT_ID_SERVICES.intValue()))
            .andExpect(jsonPath("$.servicesDesc").value(DEFAULT_SERVICES_DESC));
    }

    @Test
    @Transactional
    public void getNonExistingServices() throws Exception {
        // Get the services
        restServicesMockMvc.perform(get("/api/services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServices() throws Exception {
        // Initialize the database
        servicesRepository.saveAndFlush(services);

        int databaseSizeBeforeUpdate = servicesRepository.findAll().size();

        // Update the services
        Services updatedServices = servicesRepository.findById(services.getId()).get();
        // Disconnect from session so that the updates on updatedServices are not directly saved in db
        em.detach(updatedServices);
        updatedServices
            .idServices(UPDATED_ID_SERVICES)
            .servicesDesc(UPDATED_SERVICES_DESC);
        ServicesDTO servicesDTO = servicesMapper.toDto(updatedServices);

        restServicesMockMvc.perform(put("/api/services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(servicesDTO)))
            .andExpect(status().isOk());

        // Validate the Services in the database
        List<Services> servicesList = servicesRepository.findAll();
        assertThat(servicesList).hasSize(databaseSizeBeforeUpdate);
        Services testServices = servicesList.get(servicesList.size() - 1);
        assertThat(testServices.getIdServices()).isEqualTo(UPDATED_ID_SERVICES);
        assertThat(testServices.getServicesDesc()).isEqualTo(UPDATED_SERVICES_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingServices() throws Exception {
        int databaseSizeBeforeUpdate = servicesRepository.findAll().size();

        // Create the Services
        ServicesDTO servicesDTO = servicesMapper.toDto(services);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicesMockMvc.perform(put("/api/services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(servicesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Services in the database
        List<Services> servicesList = servicesRepository.findAll();
        assertThat(servicesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServices() throws Exception {
        // Initialize the database
        servicesRepository.saveAndFlush(services);

        int databaseSizeBeforeDelete = servicesRepository.findAll().size();

        // Delete the services
        restServicesMockMvc.perform(delete("/api/services/{id}", services.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Services> servicesList = servicesRepository.findAll();
        assertThat(servicesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
