package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.OrdersServices;
import ma.jconsulting.applications.btpproject.repository.OrdersServicesRepository;
import ma.jconsulting.applications.btpproject.service.OrdersServicesService;
import ma.jconsulting.applications.btpproject.service.dto.OrdersServicesDTO;
import ma.jconsulting.applications.btpproject.service.mapper.OrdersServicesMapper;
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
 * Integration tests for the {@link OrdersServicesResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class OrdersServicesResourceIT {

    private static final Long DEFAULT_ID_ORDERS_SERVICES = 1L;
    private static final Long UPDATED_ID_ORDERS_SERVICES = 2L;

    private static final Double DEFAULT_TARIF_JOURNALIER = 1D;
    private static final Double UPDATED_TARIF_JOURNALIER = 2D;

    private static final Double DEFAULT_JOURS_HOMME = 1D;
    private static final Double UPDATED_JOURS_HOMME = 2D;

    @Autowired
    private OrdersServicesRepository ordersServicesRepository;

    @Autowired
    private OrdersServicesMapper ordersServicesMapper;

    @Autowired
    private OrdersServicesService ordersServicesService;

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

    private MockMvc restOrdersServicesMockMvc;

    private OrdersServices ordersServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrdersServicesResource ordersServicesResource = new OrdersServicesResource(ordersServicesService);
        this.restOrdersServicesMockMvc = MockMvcBuilders.standaloneSetup(ordersServicesResource)
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
    public static OrdersServices createEntity(EntityManager em) {
        OrdersServices ordersServices = new OrdersServices()
            .idOrdersServices(DEFAULT_ID_ORDERS_SERVICES)
            .tarifJournalier(DEFAULT_TARIF_JOURNALIER)
            .joursHomme(DEFAULT_JOURS_HOMME);
        return ordersServices;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrdersServices createUpdatedEntity(EntityManager em) {
        OrdersServices ordersServices = new OrdersServices()
            .idOrdersServices(UPDATED_ID_ORDERS_SERVICES)
            .tarifJournalier(UPDATED_TARIF_JOURNALIER)
            .joursHomme(UPDATED_JOURS_HOMME);
        return ordersServices;
    }

    @BeforeEach
    public void initTest() {
        ordersServices = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrdersServices() throws Exception {
        int databaseSizeBeforeCreate = ordersServicesRepository.findAll().size();

        // Create the OrdersServices
        OrdersServicesDTO ordersServicesDTO = ordersServicesMapper.toDto(ordersServices);
        restOrdersServicesMockMvc.perform(post("/api/orders-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersServicesDTO)))
            .andExpect(status().isCreated());

        // Validate the OrdersServices in the database
        List<OrdersServices> ordersServicesList = ordersServicesRepository.findAll();
        assertThat(ordersServicesList).hasSize(databaseSizeBeforeCreate + 1);
        OrdersServices testOrdersServices = ordersServicesList.get(ordersServicesList.size() - 1);
        assertThat(testOrdersServices.getIdOrdersServices()).isEqualTo(DEFAULT_ID_ORDERS_SERVICES);
        assertThat(testOrdersServices.getTarifJournalier()).isEqualTo(DEFAULT_TARIF_JOURNALIER);
        assertThat(testOrdersServices.getJoursHomme()).isEqualTo(DEFAULT_JOURS_HOMME);
    }

    @Test
    @Transactional
    public void createOrdersServicesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordersServicesRepository.findAll().size();

        // Create the OrdersServices with an existing ID
        ordersServices.setId(1L);
        OrdersServicesDTO ordersServicesDTO = ordersServicesMapper.toDto(ordersServices);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdersServicesMockMvc.perform(post("/api/orders-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersServicesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrdersServices in the database
        List<OrdersServices> ordersServicesList = ordersServicesRepository.findAll();
        assertThat(ordersServicesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdOrdersServicesIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersServicesRepository.findAll().size();
        // set the field null
        ordersServices.setIdOrdersServices(null);

        // Create the OrdersServices, which fails.
        OrdersServicesDTO ordersServicesDTO = ordersServicesMapper.toDto(ordersServices);

        restOrdersServicesMockMvc.perform(post("/api/orders-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersServicesDTO)))
            .andExpect(status().isBadRequest());

        List<OrdersServices> ordersServicesList = ordersServicesRepository.findAll();
        assertThat(ordersServicesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrdersServices() throws Exception {
        // Initialize the database
        ordersServicesRepository.saveAndFlush(ordersServices);

        // Get all the ordersServicesList
        restOrdersServicesMockMvc.perform(get("/api/orders-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordersServices.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrdersServices").value(hasItem(DEFAULT_ID_ORDERS_SERVICES.intValue())))
            .andExpect(jsonPath("$.[*].tarifJournalier").value(hasItem(DEFAULT_TARIF_JOURNALIER.doubleValue())))
            .andExpect(jsonPath("$.[*].joursHomme").value(hasItem(DEFAULT_JOURS_HOMME.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getOrdersServices() throws Exception {
        // Initialize the database
        ordersServicesRepository.saveAndFlush(ordersServices);

        // Get the ordersServices
        restOrdersServicesMockMvc.perform(get("/api/orders-services/{id}", ordersServices.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ordersServices.getId().intValue()))
            .andExpect(jsonPath("$.idOrdersServices").value(DEFAULT_ID_ORDERS_SERVICES.intValue()))
            .andExpect(jsonPath("$.tarifJournalier").value(DEFAULT_TARIF_JOURNALIER.doubleValue()))
            .andExpect(jsonPath("$.joursHomme").value(DEFAULT_JOURS_HOMME.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrdersServices() throws Exception {
        // Get the ordersServices
        restOrdersServicesMockMvc.perform(get("/api/orders-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdersServices() throws Exception {
        // Initialize the database
        ordersServicesRepository.saveAndFlush(ordersServices);

        int databaseSizeBeforeUpdate = ordersServicesRepository.findAll().size();

        // Update the ordersServices
        OrdersServices updatedOrdersServices = ordersServicesRepository.findById(ordersServices.getId()).get();
        // Disconnect from session so that the updates on updatedOrdersServices are not directly saved in db
        em.detach(updatedOrdersServices);
        updatedOrdersServices
            .idOrdersServices(UPDATED_ID_ORDERS_SERVICES)
            .tarifJournalier(UPDATED_TARIF_JOURNALIER)
            .joursHomme(UPDATED_JOURS_HOMME);
        OrdersServicesDTO ordersServicesDTO = ordersServicesMapper.toDto(updatedOrdersServices);

        restOrdersServicesMockMvc.perform(put("/api/orders-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersServicesDTO)))
            .andExpect(status().isOk());

        // Validate the OrdersServices in the database
        List<OrdersServices> ordersServicesList = ordersServicesRepository.findAll();
        assertThat(ordersServicesList).hasSize(databaseSizeBeforeUpdate);
        OrdersServices testOrdersServices = ordersServicesList.get(ordersServicesList.size() - 1);
        assertThat(testOrdersServices.getIdOrdersServices()).isEqualTo(UPDATED_ID_ORDERS_SERVICES);
        assertThat(testOrdersServices.getTarifJournalier()).isEqualTo(UPDATED_TARIF_JOURNALIER);
        assertThat(testOrdersServices.getJoursHomme()).isEqualTo(UPDATED_JOURS_HOMME);
    }

    @Test
    @Transactional
    public void updateNonExistingOrdersServices() throws Exception {
        int databaseSizeBeforeUpdate = ordersServicesRepository.findAll().size();

        // Create the OrdersServices
        OrdersServicesDTO ordersServicesDTO = ordersServicesMapper.toDto(ordersServices);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdersServicesMockMvc.perform(put("/api/orders-services")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersServicesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrdersServices in the database
        List<OrdersServices> ordersServicesList = ordersServicesRepository.findAll();
        assertThat(ordersServicesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrdersServices() throws Exception {
        // Initialize the database
        ordersServicesRepository.saveAndFlush(ordersServices);

        int databaseSizeBeforeDelete = ordersServicesRepository.findAll().size();

        // Delete the ordersServices
        restOrdersServicesMockMvc.perform(delete("/api/orders-services/{id}", ordersServices.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrdersServices> ordersServicesList = ordersServicesRepository.findAll();
        assertThat(ordersServicesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
