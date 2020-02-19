package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.OrdersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Orders} and its DTO {@link OrdersDTO}.
 */
@Mapper(componentModel = "spring", uses = {DeliveryMapper.class, OrdersProductsMapper.class, OrdersServicesMapper.class, ReglementMapper.class})
public interface OrdersMapper extends EntityMapper<OrdersDTO, Orders> {

    @Mapping(source = "delivery.id", target = "deliveryId")
    @Mapping(source = "ordersProducts.id", target = "ordersProductsId")
    @Mapping(source = "ordersServices.id", target = "ordersServicesId")
    @Mapping(source = "reglement.id", target = "reglementId")
    OrdersDTO toDto(Orders orders);

    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "removeProject", ignore = true)
    @Mapping(target = "providers", ignore = true)
    @Mapping(target = "removeProvider", ignore = true)
    @Mapping(source = "deliveryId", target = "delivery")
    @Mapping(source = "ordersProductsId", target = "ordersProducts")
    @Mapping(source = "ordersServicesId", target = "ordersServices")
    @Mapping(source = "reglementId", target = "reglement")
    Orders toEntity(OrdersDTO ordersDTO);

    default Orders fromId(Long id) {
        if (id == null) {
            return null;
        }
        Orders orders = new Orders();
        orders.setId(id);
        return orders;
    }
}
