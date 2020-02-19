package ma.jconsulting.applications.btpproject.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrdersServicesMapperTest {

    private OrdersServicesMapper ordersServicesMapper;

    @BeforeEach
    public void setUp() {
        ordersServicesMapper = new OrdersServicesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ordersServicesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ordersServicesMapper.fromId(null)).isNull();
    }
}
