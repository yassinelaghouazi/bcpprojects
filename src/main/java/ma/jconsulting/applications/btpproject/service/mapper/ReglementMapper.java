package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.ReglementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reglement} and its DTO {@link ReglementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReglementMapper extends EntityMapper<ReglementDTO, Reglement> {


    @Mapping(target = "providers", ignore = true)
    @Mapping(target = "removeProvider", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "removeProject", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "removeOrders", ignore = true)
    @Mapping(target = "deliveries", ignore = true)
    @Mapping(target = "removeDelivery", ignore = true)
    @Mapping(target = "modePaiements", ignore = true)
    @Mapping(target = "removeModePaiement", ignore = true)
    Reglement toEntity(ReglementDTO reglementDTO);

    default Reglement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reglement reglement = new Reglement();
        reglement.setId(id);
        return reglement;
    }
}
