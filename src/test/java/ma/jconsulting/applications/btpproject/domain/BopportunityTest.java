package ma.jconsulting.applications.btpproject.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class BopportunityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bopportunity.class);
        Bopportunity bopportunity1 = new Bopportunity();
        bopportunity1.setId(1L);
        Bopportunity bopportunity2 = new Bopportunity();
        bopportunity2.setId(bopportunity1.getId());
        assertThat(bopportunity1).isEqualTo(bopportunity2);
        bopportunity2.setId(2L);
        assertThat(bopportunity1).isNotEqualTo(bopportunity2);
        bopportunity1.setId(null);
        assertThat(bopportunity1).isNotEqualTo(bopportunity2);
    }
}
