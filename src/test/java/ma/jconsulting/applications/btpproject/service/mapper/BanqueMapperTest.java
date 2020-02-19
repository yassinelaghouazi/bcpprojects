package ma.jconsulting.applications.btpproject.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BanqueMapperTest {

    private BanqueMapper banqueMapper;

    @BeforeEach
    public void setUp() {
        banqueMapper = new BanqueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(banqueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(banqueMapper.fromId(null)).isNull();
    }
}
