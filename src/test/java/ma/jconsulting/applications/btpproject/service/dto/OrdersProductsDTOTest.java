package ma.jconsulting.applications.btpproject.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class OrdersProductsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdersProductsDTO.class);
        OrdersProductsDTO ordersProductsDTO1 = new OrdersProductsDTO();
        ordersProductsDTO1.setId(1L);
        OrdersProductsDTO ordersProductsDTO2 = new OrdersProductsDTO();
        assertThat(ordersProductsDTO1).isNotEqualTo(ordersProductsDTO2);
        ordersProductsDTO2.setId(ordersProductsDTO1.getId());
        assertThat(ordersProductsDTO1).isEqualTo(ordersProductsDTO2);
        ordersProductsDTO2.setId(2L);
        assertThat(ordersProductsDTO1).isNotEqualTo(ordersProductsDTO2);
        ordersProductsDTO1.setId(null);
        assertThat(ordersProductsDTO1).isNotEqualTo(ordersProductsDTO2);
    }
}
