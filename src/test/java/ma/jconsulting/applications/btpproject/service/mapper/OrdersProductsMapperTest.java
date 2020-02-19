package ma.jconsulting.applications.btpproject.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrdersProductsMapperTest {

    private OrdersProductsMapper ordersProductsMapper;

    @BeforeEach
    public void setUp() {
        ordersProductsMapper = new OrdersProductsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ordersProductsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ordersProductsMapper.fromId(null)).isNull();
    }
}
