package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.CautionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Caution} and its DTO {@link CautionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CautionMapper extends EntityMapper<CautionDTO, Caution> {


    @Mapping(target = "bopportuniys", ignore = true)
    @Mapping(target = "removeBopportuniy", ignore = true)
    @Mapping(target = "maitreouvrages", ignore = true)
    @Mapping(target = "removeMaitreouvrage", ignore = true)
    @Mapping(target = "banques", ignore = true)
    @Mapping(target = "removeBanque", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "removeProject", ignore = true)
    Caution toEntity(CautionDTO cautionDTO);

    default Caution fromId(Long id) {
        if (id == null) {
            return null;
        }
        Caution caution = new Caution();
        caution.setId(id);
        return caution;
    }
}
