package ma.jconsulting.applications.btpproject.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class OrdersProductsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdersProducts.class);
        OrdersProducts ordersProducts1 = new OrdersProducts();
        ordersProducts1.setId(1L);
        OrdersProducts ordersProducts2 = new OrdersProducts();
        ordersProducts2.setId(ordersProducts1.getId());
        assertThat(ordersProducts1).isEqualTo(ordersProducts2);
        ordersProducts2.setId(2L);
        assertThat(ordersProducts1).isNotEqualTo(ordersProducts2);
        ordersProducts1.setId(null);
        assertThat(ordersProducts1).isNotEqualTo(ordersProducts2);
    }
}
