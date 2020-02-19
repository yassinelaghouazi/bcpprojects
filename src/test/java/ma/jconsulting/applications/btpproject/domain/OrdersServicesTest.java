package ma.jconsulting.applications.btpproject.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class OrdersServicesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdersServices.class);
        OrdersServices ordersServices1 = new OrdersServices();
        ordersServices1.setId(1L);
        OrdersServices ordersServices2 = new OrdersServices();
        ordersServices2.setId(ordersServices1.getId());
        assertThat(ordersServices1).isEqualTo(ordersServices2);
        ordersServices2.setId(2L);
        assertThat(ordersServices1).isNotEqualTo(ordersServices2);
        ordersServices1.setId(null);
        assertThat(ordersServices1).isNotEqualTo(ordersServices2);
    }
}
