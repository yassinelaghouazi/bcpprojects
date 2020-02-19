package ma.jconsulting.applications.btpproject.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ModePaiementMapperTest {

    private ModePaiementMapper modePaiementMapper;

    @BeforeEach
    public void setUp() {
        modePaiementMapper = new ModePaiementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(modePaiementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(modePaiementMapper.fromId(null)).isNull();
    }
}
