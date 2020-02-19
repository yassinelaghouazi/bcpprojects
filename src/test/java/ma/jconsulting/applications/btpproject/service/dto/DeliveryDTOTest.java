package ma.jconsulting.applications.btpproject.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class DeliveryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryDTO.class);
        DeliveryDTO deliveryDTO1 = new DeliveryDTO();
        deliveryDTO1.setId(1L);
        DeliveryDTO deliveryDTO2 = new DeliveryDTO();
        assertThat(deliveryDTO1).isNotEqualTo(deliveryDTO2);
        deliveryDTO2.setId(deliveryDTO1.getId());
        assertThat(deliveryDTO1).isEqualTo(deliveryDTO2);
        deliveryDTO2.setId(2L);
        assertThat(deliveryDTO1).isNotEqualTo(deliveryDTO2);
        deliveryDTO1.setId(null);
        assertThat(deliveryDTO1).isNotEqualTo(deliveryDTO2);
    }
}
