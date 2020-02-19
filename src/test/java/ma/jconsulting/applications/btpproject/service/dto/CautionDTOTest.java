package ma.jconsulting.applications.btpproject.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class CautionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CautionDTO.class);
        CautionDTO cautionDTO1 = new CautionDTO();
        cautionDTO1.setId(1L);
        CautionDTO cautionDTO2 = new CautionDTO();
        assertThat(cautionDTO1).isNotEqualTo(cautionDTO2);
        cautionDTO2.setId(cautionDTO1.getId());
        assertThat(cautionDTO1).isEqualTo(cautionDTO2);
        cautionDTO2.setId(2L);
        assertThat(cautionDTO1).isNotEqualTo(cautionDTO2);
        cautionDTO1.setId(null);
        assertThat(cautionDTO1).isNotEqualTo(cautionDTO2);
    }
}
