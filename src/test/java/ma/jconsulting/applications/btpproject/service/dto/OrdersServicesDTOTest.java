package ma.jconsulting.applications.btpproject.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class OrdersServicesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdersServicesDTO.class);
        OrdersServicesDTO ordersServicesDTO1 = new OrdersServicesDTO();
        ordersServicesDTO1.setId(1L);
        OrdersServicesDTO ordersServicesDTO2 = new OrdersServicesDTO();
        assertThat(ordersServicesDTO1).isNotEqualTo(ordersServicesDTO2);
        ordersServicesDTO2.setId(ordersServicesDTO1.getId());
        assertThat(ordersServicesDTO1).isEqualTo(ordersServicesDTO2);
        ordersServicesDTO2.setId(2L);
        assertThat(ordersServicesDTO1).isNotEqualTo(ordersServicesDTO2);
        ordersServicesDTO1.setId(null);
        assertThat(ordersServicesDTO1).isNotEqualTo(ordersServicesDTO2);
    }
}
