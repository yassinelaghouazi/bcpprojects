package ma.jconsulting.applications.btpproject.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MaitreOuvrageMapperTest {

    private MaitreOuvrageMapper maitreOuvrageMapper;

    @BeforeEach
    public void setUp() {
        maitreOuvrageMapper = new MaitreOuvrageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(maitreOuvrageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(maitreOuvrageMapper.fromId(null)).isNull();
    }
}
