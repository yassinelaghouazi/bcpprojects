package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.OrdersProductsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrdersProducts} and its DTO {@link OrdersProductsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrdersProductsMapper extends EntityMapper<OrdersProductsDTO, OrdersProducts> {


    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "removeOrders", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "removeProducts", ignore = true)
    OrdersProducts toEntity(OrdersProductsDTO ordersProductsDTO);

    default OrdersProducts fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrdersProducts ordersProducts = new OrdersProducts();
        ordersProducts.setId(id);
        return ordersProducts;
    }
}
