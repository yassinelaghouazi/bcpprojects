package ma.jconsulting.applications.btpproject.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class BopportunityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BopportunityDTO.class);
        BopportunityDTO bopportunityDTO1 = new BopportunityDTO();
        bopportunityDTO1.setId(1L);
        BopportunityDTO bopportunityDTO2 = new BopportunityDTO();
        assertThat(bopportunityDTO1).isNotEqualTo(bopportunityDTO2);
        bopportunityDTO2.setId(bopportunityDTO1.getId());
        assertThat(bopportunityDTO1).isEqualTo(bopportunityDTO2);
        bopportunityDTO2.setId(2L);
        assertThat(bopportunityDTO1).isNotEqualTo(bopportunityDTO2);
        bopportunityDTO1.setId(null);
        assertThat(bopportunityDTO1).isNotEqualTo(bopportunityDTO2);
    }
}
