package ma.jconsulting.applications.btpproject.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BopportunityMapperTest {

    private BopportunityMapper bopportunityMapper;

    @BeforeEach
    public void setUp() {
        bopportunityMapper = new BopportunityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(bopportunityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bopportunityMapper.fromId(null)).isNull();
    }
}
