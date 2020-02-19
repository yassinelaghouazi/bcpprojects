package ma.jconsulting.applications.btpproject.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.jconsulting.applications.btpproject.web.rest.TestUtil;

public class ModePaiementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModePaiement.class);
        ModePaiement modePaiement1 = new ModePaiement();
        modePaiement1.setId(1L);
        ModePaiement modePaiement2 = new ModePaiement();
        modePaiement2.setId(modePaiement1.getId());
        assertThat(modePaiement1).isEqualTo(modePaiement2);
        modePaiement2.setId(2L);
        assertThat(modePaiement1).isNotEqualTo(modePaiement2);
        modePaiement1.setId(null);
        assertThat(modePaiement1).isNotEqualTo(modePaiement2);
    }
}
