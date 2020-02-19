package ma.jconsulting.applications.btpproject.web.rest;

import ma.jconsulting.applications.btpproject.BtpprojectsApp;
import ma.jconsulting.applications.btpproject.domain.OrdersProducts;
import ma.jconsulting.applications.btpproject.repository.OrdersProductsRepository;
import ma.jconsulting.applications.btpproject.service.OrdersProductsService;
import ma.jconsulting.applications.btpproject.service.dto.OrdersProductsDTO;
import ma.jconsulting.applications.btpproject.service.mapper.OrdersProductsMapper;
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
 * Integration tests for the {@link OrdersProductsResource} REST controller.
 */
@SpringBootTest(classes = BtpprojectsApp.class)
public class OrdersProductsResourceIT {

    private static final Long DEFAULT_ID_ORDERS_PRODUCTS = 1L;
    private static final Long UPDATED_ID_ORDERS_PRODUCTS = 2L;

    private static final Double DEFAULT_UNIT_PRICE = 1D;
    private static final Double UPDATED_UNIT_PRICE = 2D;

    private static final Double DEFAULT_QUANTITE = 1D;
    private static final Double UPDATED_QUANTITE = 2D;

    private static final Double DEFAULT_TVA = 1D;
    private static final Double UPDATED_TVA = 2D;

    private static final Double DEFAULT_TOTAL_HT = 1D;
    private static final Double UPDATED_TOTAL_HT = 2D;

    private static final Double DEFAULT_MONTANT_TVA = 1D;
    private static final Double UPDATED_MONTANT_TVA = 2D;

    private static final Double DEFAULT_TOTAL_TTC = 1D;
    private static final Double UPDATED_TOTAL_TTC = 2D;

    @Autowired
    private OrdersProductsRepository ordersProductsRepository;

    @Autowired
    private OrdersProductsMapper ordersProductsMapper;

    @Autowired
    private OrdersProductsService ordersProductsService;

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

    private MockMvc restOrdersProductsMockMvc;

    private OrdersProducts ordersProducts;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrdersProductsResource ordersProductsResource = new OrdersProductsResource(ordersProductsService);
        this.restOrdersProductsMockMvc = MockMvcBuilders.standaloneSetup(ordersProductsResource)
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
    public static OrdersProducts createEntity(EntityManager em) {
        OrdersProducts ordersProducts = new OrdersProducts()
            .idOrdersProducts(DEFAULT_ID_ORDERS_PRODUCTS)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .quantite(DEFAULT_QUANTITE)
            .tva(DEFAULT_TVA)
            .totalHT(DEFAULT_TOTAL_HT)
            .montantTVA(DEFAULT_MONTANT_TVA)
            .totalTTC(DEFAULT_TOTAL_TTC);
        return ordersProducts;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrdersProducts createUpdatedEntity(EntityManager em) {
        OrdersProducts ordersProducts = new OrdersProducts()
            .idOrdersProducts(UPDATED_ID_ORDERS_PRODUCTS)
            .unitPrice(UPDATED_UNIT_PRICE)
            .quantite(UPDATED_QUANTITE)
            .tva(UPDATED_TVA)
            .totalHT(UPDATED_TOTAL_HT)
            .montantTVA(UPDATED_MONTANT_TVA)
            .totalTTC(UPDATED_TOTAL_TTC);
        return ordersProducts;
    }

    @BeforeEach
    public void initTest() {
        ordersProducts = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrdersProducts() throws Exception {
        int databaseSizeBeforeCreate = ordersProductsRepository.findAll().size();

        // Create the OrdersProducts
        OrdersProductsDTO ordersProductsDTO = ordersProductsMapper.toDto(ordersProducts);
        restOrdersProductsMockMvc.perform(post("/api/orders-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersProductsDTO)))
            .andExpect(status().isCreated());

        // Validate the OrdersProducts in the database
        List<OrdersProducts> ordersProductsList = ordersProductsRepository.findAll();
        assertThat(ordersProductsList).hasSize(databaseSizeBeforeCreate + 1);
        OrdersProducts testOrdersProducts = ordersProductsList.get(ordersProductsList.size() - 1);
        assertThat(testOrdersProducts.getIdOrdersProducts()).isEqualTo(DEFAULT_ID_ORDERS_PRODUCTS);
        assertThat(testOrdersProducts.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testOrdersProducts.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testOrdersProducts.getTva()).isEqualTo(DEFAULT_TVA);
        assertThat(testOrdersProducts.getTotalHT()).isEqualTo(DEFAULT_TOTAL_HT);
        assertThat(testOrdersProducts.getMontantTVA()).isEqualTo(DEFAULT_MONTANT_TVA);
        assertThat(testOrdersProducts.getTotalTTC()).isEqualTo(DEFAULT_TOTAL_TTC);
    }

    @Test
    @Transactional
    public void createOrdersProductsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordersProductsRepository.findAll().size();

        // Create the OrdersProducts with an existing ID
        ordersProducts.setId(1L);
        OrdersProductsDTO ordersProductsDTO = ordersProductsMapper.toDto(ordersProducts);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdersProductsMockMvc.perform(post("/api/orders-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersProductsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrdersProducts in the database
        List<OrdersProducts> ordersProductsList = ordersProductsRepository.findAll();
        assertThat(ordersProductsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdOrdersProductsIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersProductsRepository.findAll().size();
        // set the field null
        ordersProducts.setIdOrdersProducts(null);

        // Create the OrdersProducts, which fails.
        OrdersProductsDTO ordersProductsDTO = ordersProductsMapper.toDto(ordersProducts);

        restOrdersProductsMockMvc.perform(post("/api/orders-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersProductsDTO)))
            .andExpect(status().isBadRequest());

        List<OrdersProducts> ordersProductsList = ordersProductsRepository.findAll();
        assertThat(ordersProductsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrdersProducts() throws Exception {
        // Initialize the database
        ordersProductsRepository.saveAndFlush(ordersProducts);

        // Get all the ordersProductsList
        restOrdersProductsMockMvc.perform(get("/api/orders-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordersProducts.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrdersProducts").value(hasItem(DEFAULT_ID_ORDERS_PRODUCTS.intValue())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.doubleValue())))
            .andExpect(jsonPath("$.[*].tva").value(hasItem(DEFAULT_TVA.doubleValue())))
            .andExpect(jsonPath("$.[*].totalHT").value(hasItem(DEFAULT_TOTAL_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].montantTVA").value(hasItem(DEFAULT_MONTANT_TVA.doubleValue())))
            .andExpect(jsonPath("$.[*].totalTTC").value(hasItem(DEFAULT_TOTAL_TTC.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getOrdersProducts() throws Exception {
        // Initialize the database
        ordersProductsRepository.saveAndFlush(ordersProducts);

        // Get the ordersProducts
        restOrdersProductsMockMvc.perform(get("/api/orders-products/{id}", ordersProducts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ordersProducts.getId().intValue()))
            .andExpect(jsonPath("$.idOrdersProducts").value(DEFAULT_ID_ORDERS_PRODUCTS.intValue()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE.doubleValue()))
            .andExpect(jsonPath("$.tva").value(DEFAULT_TVA.doubleValue()))
            .andExpect(jsonPath("$.totalHT").value(DEFAULT_TOTAL_HT.doubleValue()))
            .andExpect(jsonPath("$.montantTVA").value(DEFAULT_MONTANT_TVA.doubleValue()))
            .andExpect(jsonPath("$.totalTTC").value(DEFAULT_TOTAL_TTC.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrdersProducts() throws Exception {
        // Get the ordersProducts
        restOrdersProductsMockMvc.perform(get("/api/orders-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdersProducts() throws Exception {
        // Initialize the database
        ordersProductsRepository.saveAndFlush(ordersProducts);

        int databaseSizeBeforeUpdate = ordersProductsRepository.findAll().size();

        // Update the ordersProducts
        OrdersProducts updatedOrdersProducts = ordersProductsRepository.findById(ordersProducts.getId()).get();
        // Disconnect from session so that the updates on updatedOrdersProducts are not directly saved in db
        em.detach(updatedOrdersProducts);
        updatedOrdersProducts
            .idOrdersProducts(UPDATED_ID_ORDERS_PRODUCTS)
            .unitPrice(UPDATED_UNIT_PRICE)
            .quantite(UPDATED_QUANTITE)
            .tva(UPDATED_TVA)
            .totalHT(UPDATED_TOTAL_HT)
            .montantTVA(UPDATED_MONTANT_TVA)
            .totalTTC(UPDATED_TOTAL_TTC);
        OrdersProductsDTO ordersProductsDTO = ordersProductsMapper.toDto(updatedOrdersProducts);

        restOrdersProductsMockMvc.perform(put("/api/orders-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersProductsDTO)))
            .andExpect(status().isOk());

        // Validate the OrdersProducts in the database
        List<OrdersProducts> ordersProductsList = ordersProductsRepository.findAll();
        assertThat(ordersProductsList).hasSize(databaseSizeBeforeUpdate);
        OrdersProducts testOrdersProducts = ordersProductsList.get(ordersProductsList.size() - 1);
        assertThat(testOrdersProducts.getIdOrdersProducts()).isEqualTo(UPDATED_ID_ORDERS_PRODUCTS);
        assertThat(testOrdersProducts.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testOrdersProducts.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testOrdersProducts.getTva()).isEqualTo(UPDATED_TVA);
        assertThat(testOrdersProducts.getTotalHT()).isEqualTo(UPDATED_TOTAL_HT);
        assertThat(testOrdersProducts.getMontantTVA()).isEqualTo(UPDATED_MONTANT_TVA);
        assertThat(testOrdersProducts.getTotalTTC()).isEqualTo(UPDATED_TOTAL_TTC);
    }

    @Test
    @Transactional
    public void updateNonExistingOrdersProducts() throws Exception {
        int databaseSizeBeforeUpdate = ordersProductsRepository.findAll().size();

        // Create the OrdersProducts
        OrdersProductsDTO ordersProductsDTO = ordersProductsMapper.toDto(ordersProducts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdersProductsMockMvc.perform(put("/api/orders-products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersProductsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrdersProducts in the database
        List<OrdersProducts> ordersProductsList = ordersProductsRepository.findAll();
        assertThat(ordersProductsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrdersProducts() throws Exception {
        // Initialize the database
        ordersProductsRepository.saveAndFlush(ordersProducts);

        int databaseSizeBeforeDelete = ordersProductsRepository.findAll().size();

        // Delete the ordersProducts
        restOrdersProductsMockMvc.perform(delete("/api/orders-products/{id}", ordersProducts.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrdersProducts> ordersProductsList = ordersProductsRepository.findAll();
        assertThat(ordersProductsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
