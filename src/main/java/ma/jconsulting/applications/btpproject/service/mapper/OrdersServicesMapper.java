package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.OrdersServicesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrdersServices} and its DTO {@link OrdersServicesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrdersServicesMapper extends EntityMapper<OrdersServicesDTO, OrdersServices> {


    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "removeOrders", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "removeServices", ignore = true)
    OrdersServices toEntity(OrdersServicesDTO ordersServicesDTO);

    default OrdersServices fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrdersServices ordersServices = new OrdersServices();
        ordersServices.setId(id);
        return ordersServices;
    }
}
