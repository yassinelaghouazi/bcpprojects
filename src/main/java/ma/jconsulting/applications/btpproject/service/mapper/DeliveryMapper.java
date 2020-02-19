package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.DeliveryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Delivery} and its DTO {@link DeliveryDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReglementMapper.class})
public interface DeliveryMapper extends EntityMapper<DeliveryDTO, Delivery> {

    @Mapping(source = "reglement.id", target = "reglementId")
    DeliveryDTO toDto(Delivery delivery);

    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "removeProject", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "removeOrders", ignore = true)
    @Mapping(source = "reglementId", target = "reglement")
    Delivery toEntity(DeliveryDTO deliveryDTO);

    default Delivery fromId(Long id) {
        if (id == null) {
            return null;
        }
        Delivery delivery = new Delivery();
        delivery.setId(id);
        return delivery;
    }
}
