package ma.jconsulting.applications.btpproject.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryMapperTest {

    private DeliveryMapper deliveryMapper;

    @BeforeEach
    public void setUp() {
        deliveryMapper = new DeliveryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(deliveryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(deliveryMapper.fromId(null)).isNull();
    }
}
