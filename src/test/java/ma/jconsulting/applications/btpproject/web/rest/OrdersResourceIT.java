package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.Orders;
import ma.jconsulting.applications.btpproject.repository.OrdersRepository;
import ma.jconsulting.applications.btpproject.service.OrdersService;
import ma.jconsulting.applications.btpproject.service.dto.OrdersDTO;
import ma.jconsulting.applications.btpproject.service.mapper.OrdersMapper;
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
 * Integration tests for the {@link OrdersResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class OrdersResourceIT {

    private static final Long DEFAULT_ID_ORDERS = 1L;
    private static final Long UPDATED_ID_ORDERS = 2L;

    private static final String DEFAULT_ORDERS_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ORDERS_DESC = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTALHT = 1D;
    private static final Double UPDATED_TOTALHT = 2D;

    private static final Double DEFAULT_TOTALTVA = 1D;
    private static final Double UPDATED_TOTALTVA = 2D;

    private static final Double DEFAULT_TOTALTTC = 1D;
    private static final Double UPDATED_TOTALTTC = 2D;

    private static final LocalDate DEFAULT_ORDERS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORDERS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EXPECTED_DELIVERY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPECTED_DELIVERY = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_TVAMOYENNE = 1D;
    private static final Double UPDATED_TVAMOYENNE = 2D;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrdersService ordersService;

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

    private MockMvc restOrdersMockMvc;

    private Orders orders;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrdersResource ordersResource = new OrdersResource(ordersService);
        this.restOrdersMockMvc = MockMvcBuilders.standaloneSetup(ordersResource)
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
    public static Orders createEntity(EntityManager em) {
        Orders orders = new Orders()
            .idOrders(DEFAULT_ID_ORDERS)
            .ordersDesc(DEFAULT_ORDERS_DESC)
            .totalht(DEFAULT_TOTALHT)
            .totaltva(DEFAULT_TOTALTVA)
            .totalttc(DEFAULT_TOTALTTC)
            .ordersDate(DEFAULT_ORDERS_DATE)
            .expectedDelivery(DEFAULT_EXPECTED_DELIVERY)
            .tvamoyenne(DEFAULT_TVAMOYENNE);
        return orders;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Orders createUpdatedEntity(EntityManager em) {
        Orders orders = new Orders()
            .idOrders(UPDATED_ID_ORDERS)
            .ordersDesc(UPDATED_ORDERS_DESC)
            .totalht(UPDATED_TOTALHT)
            .totaltva(UPDATED_TOTALTVA)
            .totalttc(UPDATED_TOTALTTC)
            .ordersDate(UPDATED_ORDERS_DATE)
            .expectedDelivery(UPDATED_EXPECTED_DELIVERY)
            .tvamoyenne(UPDATED_TVAMOYENNE);
        return orders;
    }

    @BeforeEach
    public void initTest() {
        orders = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrders() throws Exception {
        int databaseSizeBeforeCreate = ordersRepository.findAll().size();

        // Create the Orders
        OrdersDTO ordersDTO = ordersMapper.toDto(orders);
        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersDTO)))
            .andExpect(status().isCreated());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeCreate + 1);
        Orders testOrders = ordersList.get(ordersList.size() - 1);
        assertThat(testOrders.getIdOrders()).isEqualTo(DEFAULT_ID_ORDERS);
        assertThat(testOrders.getOrdersDesc()).isEqualTo(DEFAULT_ORDERS_DESC);
        assertThat(testOrders.getTotalht()).isEqualTo(DEFAULT_TOTALHT);
        assertThat(testOrders.getTotaltva()).isEqualTo(DEFAULT_TOTALTVA);
        assertThat(testOrders.getTotalttc()).isEqualTo(DEFAULT_TOTALTTC);
        assertThat(testOrders.getOrdersDate()).isEqualTo(DEFAULT_ORDERS_DATE);
        assertThat(testOrders.getExpectedDelivery()).isEqualTo(DEFAULT_EXPECTED_DELIVERY);
        assertThat(testOrders.getTvamoyenne()).isEqualTo(DEFAULT_TVAMOYENNE);
    }

    @Test
    @Transactional
    public void createOrdersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordersRepository.findAll().size();

        // Create the Orders with an existing ID
        orders.setId(1L);
        OrdersDTO ordersDTO = ordersMapper.toDto(orders);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdOrdersIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setIdOrders(null);

        // Create the Orders, which fails.
        OrdersDTO ordersDTO = ordersMapper.toDto(orders);

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersDTO)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList
        restOrdersMockMvc.perform(get("/api/orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orders.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrders").value(hasItem(DEFAULT_ID_ORDERS.intValue())))
            .andExpect(jsonPath("$.[*].ordersDesc").value(hasItem(DEFAULT_ORDERS_DESC)))
            .andExpect(jsonPath("$.[*].totalht").value(hasItem(DEFAULT_TOTALHT.doubleValue())))
            .andExpect(jsonPath("$.[*].totaltva").value(hasItem(DEFAULT_TOTALTVA.doubleValue())))
            .andExpect(jsonPath("$.[*].totalttc").value(hasItem(DEFAULT_TOTALTTC.doubleValue())))
            .andExpect(jsonPath("$.[*].ordersDate").value(hasItem(DEFAULT_ORDERS_DATE.toString())))
            .andExpect(jsonPath("$.[*].expectedDelivery").value(hasItem(DEFAULT_EXPECTED_DELIVERY.toString())))
            .andExpect(jsonPath("$.[*].tvamoyenne").value(hasItem(DEFAULT_TVAMOYENNE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get the orders
        restOrdersMockMvc.perform(get("/api/orders/{id}", orders.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orders.getId().intValue()))
            .andExpect(jsonPath("$.idOrders").value(DEFAULT_ID_ORDERS.intValue()))
            .andExpect(jsonPath("$.ordersDesc").value(DEFAULT_ORDERS_DESC))
            .andExpect(jsonPath("$.totalht").value(DEFAULT_TOTALHT.doubleValue()))
            .andExpect(jsonPath("$.totaltva").value(DEFAULT_TOTALTVA.doubleValue()))
            .andExpect(jsonPath("$.totalttc").value(DEFAULT_TOTALTTC.doubleValue()))
            .andExpect(jsonPath("$.ordersDate").value(DEFAULT_ORDERS_DATE.toString()))
            .andExpect(jsonPath("$.expectedDelivery").value(DEFAULT_EXPECTED_DELIVERY.toString()))
            .andExpect(jsonPath("$.tvamoyenne").value(DEFAULT_TVAMOYENNE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrders() throws Exception {
        // Get the orders
        restOrdersMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        int databaseSizeBeforeUpdate = ordersRepository.findAll().size();

        // Update the orders
        Orders updatedOrders = ordersRepository.findById(orders.getId()).get();
        // Disconnect from session so that the updates on updatedOrders are not directly saved in db
        em.detach(updatedOrders);
        updatedOrders
            .idOrders(UPDATED_ID_ORDERS)
            .ordersDesc(UPDATED_ORDERS_DESC)
            .totalht(UPDATED_TOTALHT)
            .totaltva(UPDATED_TOTALTVA)
            .totalttc(UPDATED_TOTALTTC)
            .ordersDate(UPDATED_ORDERS_DATE)
            .expectedDelivery(UPDATED_EXPECTED_DELIVERY)
            .tvamoyenne(UPDATED_TVAMOYENNE);
        OrdersDTO ordersDTO = ordersMapper.toDto(updatedOrders);

        restOrdersMockMvc.perform(put("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersDTO)))
            .andExpect(status().isOk());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeUpdate);
        Orders testOrders = ordersList.get(ordersList.size() - 1);
        assertThat(testOrders.getIdOrders()).isEqualTo(UPDATED_ID_ORDERS);
        assertThat(testOrders.getOrdersDesc()).isEqualTo(UPDATED_ORDERS_DESC);
        assertThat(testOrders.getTotalht()).isEqualTo(UPDATED_TOTALHT);
        assertThat(testOrders.getTotaltva()).isEqualTo(UPDATED_TOTALTVA);
        assertThat(testOrders.getTotalttc()).isEqualTo(UPDATED_TOTALTTC);
        assertThat(testOrders.getOrdersDate()).isEqualTo(UPDATED_ORDERS_DATE);
        assertThat(testOrders.getExpectedDelivery()).isEqualTo(UPDATED_EXPECTED_DELIVERY);
        assertThat(testOrders.getTvamoyenne()).isEqualTo(UPDATED_TVAMOYENNE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrders() throws Exception {
        int databaseSizeBeforeUpdate = ordersRepository.findAll().size();

        // Create the Orders
        OrdersDTO ordersDTO = ordersMapper.toDto(orders);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdersMockMvc.perform(put("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        int databaseSizeBeforeDelete = ordersRepository.findAll().size();

        // Delete the orders
        restOrdersMockMvc.perform(delete("/api/orders/{id}", orders.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
