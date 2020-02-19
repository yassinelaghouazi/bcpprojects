package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.MaitreOuvrageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MaitreOuvrage} and its DTO {@link MaitreOuvrageDTO}.
 */
@Mapper(componentModel = "spring", uses = {BopportunityMapper.class, CautionMapper.class})
public interface MaitreOuvrageMapper extends EntityMapper<MaitreOuvrageDTO, MaitreOuvrage> {

    @Mapping(source = "bopportunity.id", target = "bopportunityId")
    @Mapping(source = "caution.id", target = "cautionId")
    MaitreOuvrageDTO toDto(MaitreOuvrage maitreOuvrage);

    @Mapping(source = "bopportunityId", target = "bopportunity")
    @Mapping(source = "cautionId", target = "caution")
    MaitreOuvrage toEntity(MaitreOuvrageDTO maitreOuvrageDTO);

    default MaitreOuvrage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MaitreOuvrage maitreOuvrage = new MaitreOuvrage();
        maitreOuvrage.setId(id);
        return maitreOuvrage;
    }
}
