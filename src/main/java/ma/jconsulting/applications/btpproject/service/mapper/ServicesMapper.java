package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.ServicesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Services} and its DTO {@link ServicesDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrdersServicesMapper.class})
public interface ServicesMapper extends EntityMapper<ServicesDTO, Services> {

    @Mapping(source = "ordersServices.id", target = "ordersServicesId")
    ServicesDTO toDto(Services services);

    @Mapping(source = "ordersServicesId", target = "ordersServices")
    Services toEntity(ServicesDTO servicesDTO);

    default Services fromId(Long id) {
        if (id == null) {
            return null;
        }
        Services services = new Services();
        services.setId(id);
        return services;
    }
}
