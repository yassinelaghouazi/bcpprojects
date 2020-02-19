package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.BopportunityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bopportunity} and its DTO {@link BopportunityDTO}.
 */
@Mapper(componentModel = "spring", uses = {CautionMapper.class, ProjectMapper.class})
public interface BopportunityMapper extends EntityMapper<BopportunityDTO, Bopportunity> {

    @Mapping(source = "caution.id", target = "cautionId")
    @Mapping(source = "project.id", target = "projectId")
    BopportunityDTO toDto(Bopportunity bopportunity);

    @Mapping(target = "maitreOuvrages", ignore = true)
    @Mapping(target = "removeMaitreOuvrage", ignore = true)
    @Mapping(target = "companies", ignore = true)
    @Mapping(target = "removeCompany", ignore = true)
    @Mapping(source = "cautionId", target = "caution")
    @Mapping(source = "projectId", target = "project")
    Bopportunity toEntity(BopportunityDTO bopportunityDTO);

    default Bopportunity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bopportunity bopportunity = new Bopportunity();
        bopportunity.setId(id);
        return bopportunity;
    }
}
