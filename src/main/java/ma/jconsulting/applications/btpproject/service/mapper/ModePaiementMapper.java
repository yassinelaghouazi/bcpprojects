package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.ModePaiementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ModePaiement} and its DTO {@link ModePaiementDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReglementMapper.class})
public interface ModePaiementMapper extends EntityMapper<ModePaiementDTO, ModePaiement> {

    @Mapping(source = "reglement.id", target = "reglementId")
    ModePaiementDTO toDto(ModePaiement modePaiement);

    @Mapping(source = "reglementId", target = "reglement")
    ModePaiement toEntity(ModePaiementDTO modePaiementDTO);

    default ModePaiement fromId(Long id) {
        if (id == null) {
            return null;
        }
        ModePaiement modePaiement = new ModePaiement();
        modePaiement.setId(id);
        return modePaiement;
    }
}
