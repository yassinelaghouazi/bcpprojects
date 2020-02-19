package ma.jconsulting.applications.btpproject.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CautionMapperTest {

    private CautionMapper cautionMapper;

    @BeforeEach
    public void setUp() {
        cautionMapper = new CautionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cautionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cautionMapper.fromId(null)).isNull();
    }
}
