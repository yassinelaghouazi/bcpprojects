package ma.jconsulting.applications.btpproject.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class MaitreOuvrageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaitreOuvrageDTO.class);
        MaitreOuvrageDTO maitreOuvrageDTO1 = new MaitreOuvrageDTO();
        maitreOuvrageDTO1.setId(1L);
        MaitreOuvrageDTO maitreOuvrageDTO2 = new MaitreOuvrageDTO();
        assertThat(maitreOuvrageDTO1).isNotEqualTo(maitreOuvrageDTO2);
        maitreOuvrageDTO2.setId(maitreOuvrageDTO1.getId());
        assertThat(maitreOuvrageDTO1).isEqualTo(maitreOuvrageDTO2);
        maitreOuvrageDTO2.setId(2L);
        assertThat(maitreOuvrageDTO1).isNotEqualTo(maitreOuvrageDTO2);
        maitreOuvrageDTO1.setId(null);
        assertThat(maitreOuvrageDTO1).isNotEqualTo(maitreOuvrageDTO2);
    }
}
