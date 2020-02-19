package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.Delivery;
import ma.jconsulting.applications.btpproject.repository.DeliveryRepository;
import ma.jconsulting.applications.btpproject.service.DeliveryService;
import ma.jconsulting.applications.btpproject.service.dto.DeliveryDTO;
import ma.jconsulting.applications.btpproject.service.mapper.DeliveryMapper;
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
 * Integration tests for the {@link DeliveryResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class DeliveryResourceIT {

    private static final Long DEFAULT_ID_DELIVERY = 1L;
    private static final Long UPDATED_ID_DELIVERY = 2L;

    private static final String DEFAULT_DELIVERY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DELIVERY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_SUB_TOTAL = 1D;
    private static final Double UPDATED_SUB_TOTAL = 2D;

    private static final Double DEFAULT_VATAMOUNT = 1D;
    private static final Double UPDATED_VATAMOUNT = 2D;

    private static final Double DEFAULT_TOTAL = 1D;
    private static final Double UPDATED_TOTAL = 2D;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private DeliveryService deliveryService;

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

    private MockMvc restDeliveryMockMvc;

    private Delivery delivery;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeliveryResource deliveryResource = new DeliveryResource(deliveryService);
        this.restDeliveryMockMvc = MockMvcBuilders.standaloneSetup(deliveryResource)
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
    public static Delivery createEntity(EntityManager em) {
        Delivery delivery = new Delivery()
            .idDelivery(DEFAULT_ID_DELIVERY)
            .deliveryDescription(DEFAULT_DELIVERY_DESCRIPTION)
            .deliveryDate(DEFAULT_DELIVERY_DATE)
            .subTotal(DEFAULT_SUB_TOTAL)
            .vatamount(DEFAULT_VATAMOUNT)
            .total(DEFAULT_TOTAL);
        return delivery;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Delivery createUpdatedEntity(EntityManager em) {
        Delivery delivery = new Delivery()
            .idDelivery(UPDATED_ID_DELIVERY)
            .deliveryDescription(UPDATED_DELIVERY_DESCRIPTION)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .subTotal(UPDATED_SUB_TOTAL)
            .vatamount(UPDATED_VATAMOUNT)
            .total(UPDATED_TOTAL);
        return delivery;
    }

    @BeforeEach
    public void initTest() {
        delivery = createEntity(em);
    }

    @Test
    @Transactional
    public void createDelivery() throws Exception {
        int databaseSizeBeforeCreate = deliveryRepository.findAll().size();

        // Create the Delivery
        DeliveryDTO deliveryDTO = deliveryMapper.toDto(delivery);
        restDeliveryMockMvc.perform(post("/api/deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryDTO)))
            .andExpect(status().isCreated());

        // Validate the Delivery in the database
        List<Delivery> deliveryList = deliveryRepository.findAll();
        assertThat(deliveryList).hasSize(databaseSizeBeforeCreate + 1);
        Delivery testDelivery = deliveryList.get(deliveryList.size() - 1);
        assertThat(testDelivery.getIdDelivery()).isEqualTo(DEFAULT_ID_DELIVERY);
        assertThat(testDelivery.getDeliveryDescription()).isEqualTo(DEFAULT_DELIVERY_DESCRIPTION);
        assertThat(testDelivery.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testDelivery.getSubTotal()).isEqualTo(DEFAULT_SUB_TOTAL);
        assertThat(testDelivery.getVatamount()).isEqualTo(DEFAULT_VATAMOUNT);
        assertThat(testDelivery.getTotal()).isEqualTo(DEFAULT_TOTAL);
    }

    @Test
    @Transactional
    public void createDeliveryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliveryRepository.findAll().size();

        // Create the Delivery with an existing ID
        delivery.setId(1L);
        DeliveryDTO deliveryDTO = deliveryMapper.toDto(delivery);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryMockMvc.perform(post("/api/deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Delivery in the database
        List<Delivery> deliveryList = deliveryRepository.findAll();
        assertThat(deliveryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdDeliveryIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryRepository.findAll().size();
        // set the field null
        delivery.setIdDelivery(null);

        // Create the Delivery, which fails.
        DeliveryDTO deliveryDTO = deliveryMapper.toDto(delivery);

        restDeliveryMockMvc.perform(post("/api/deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryDTO)))
            .andExpect(status().isBadRequest());

        List<Delivery> deliveryList = deliveryRepository.findAll();
        assertThat(deliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeliveries() throws Exception {
        // Initialize the database
        deliveryRepository.saveAndFlush(delivery);

        // Get all the deliveryList
        restDeliveryMockMvc.perform(get("/api/deliveries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(delivery.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDelivery").value(hasItem(DEFAULT_ID_DELIVERY.intValue())))
            .andExpect(jsonPath("$.[*].deliveryDescription").value(hasItem(DEFAULT_DELIVERY_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(DEFAULT_DELIVERY_DATE.toString())))
            .andExpect(jsonPath("$.[*].subTotal").value(hasItem(DEFAULT_SUB_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].vatamount").value(hasItem(DEFAULT_VATAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDelivery() throws Exception {
        // Initialize the database
        deliveryRepository.saveAndFlush(delivery);

        // Get the delivery
        restDeliveryMockMvc.perform(get("/api/deliveries/{id}", delivery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(delivery.getId().intValue()))
            .andExpect(jsonPath("$.idDelivery").value(DEFAULT_ID_DELIVERY.intValue()))
            .andExpect(jsonPath("$.deliveryDescription").value(DEFAULT_DELIVERY_DESCRIPTION))
            .andExpect(jsonPath("$.deliveryDate").value(DEFAULT_DELIVERY_DATE.toString()))
            .andExpect(jsonPath("$.subTotal").value(DEFAULT_SUB_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.vatamount").value(DEFAULT_VATAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDelivery() throws Exception {
        // Get the delivery
        restDeliveryMockMvc.perform(get("/api/deliveries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDelivery() throws Exception {
        // Initialize the database
        deliveryRepository.saveAndFlush(delivery);

        int databaseSizeBeforeUpdate = deliveryRepository.findAll().size();

        // Update the delivery
        Delivery updatedDelivery = deliveryRepository.findById(delivery.getId()).get();
        // Disconnect from session so that the updates on updatedDelivery are not directly saved in db
        em.detach(updatedDelivery);
        updatedDelivery
            .idDelivery(UPDATED_ID_DELIVERY)
            .deliveryDescription(UPDATED_DELIVERY_DESCRIPTION)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .subTotal(UPDATED_SUB_TOTAL)
            .vatamount(UPDATED_VATAMOUNT)
            .total(UPDATED_TOTAL);
        DeliveryDTO deliveryDTO = deliveryMapper.toDto(updatedDelivery);

        restDeliveryMockMvc.perform(put("/api/deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryDTO)))
            .andExpect(status().isOk());

        // Validate the Delivery in the database
        List<Delivery> deliveryList = deliveryRepository.findAll();
        assertThat(deliveryList).hasSize(databaseSizeBeforeUpdate);
        Delivery testDelivery = deliveryList.get(deliveryList.size() - 1);
        assertThat(testDelivery.getIdDelivery()).isEqualTo(UPDATED_ID_DELIVERY);
        assertThat(testDelivery.getDeliveryDescription()).isEqualTo(UPDATED_DELIVERY_DESCRIPTION);
        assertThat(testDelivery.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testDelivery.getSubTotal()).isEqualTo(UPDATED_SUB_TOTAL);
        assertThat(testDelivery.getVatamount()).isEqualTo(UPDATED_VATAMOUNT);
        assertThat(testDelivery.getTotal()).isEqualTo(UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingDelivery() throws Exception {
        int databaseSizeBeforeUpdate = deliveryRepository.findAll().size();

        // Create the Delivery
        DeliveryDTO deliveryDTO = deliveryMapper.toDto(delivery);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryMockMvc.perform(put("/api/deliveries")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deliveryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Delivery in the database
        List<Delivery> deliveryList = deliveryRepository.findAll();
        assertThat(deliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDelivery() throws Exception {
        // Initialize the database
        deliveryRepository.saveAndFlush(delivery);

        int databaseSizeBeforeDelete = deliveryRepository.findAll().size();

        // Delete the delivery
        restDeliveryMockMvc.perform(delete("/api/deliveries/{id}", delivery.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Delivery> deliveryList = deliveryRepository.findAll();
        assertThat(deliveryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
