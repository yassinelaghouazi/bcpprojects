package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.ProductsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Products} and its DTO {@link ProductsDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrdersProductsMapper.class})
public interface ProductsMapper extends EntityMapper<ProductsDTO, Products> {

    @Mapping(source = "ordersProducts.id", target = "ordersProductsId")
    ProductsDTO toDto(Products products);

    @Mapping(target = "recommendedProviders", ignore = true)
    @Mapping(target = "removeRecommendedProvider", ignore = true)
    @Mapping(source = "ordersProductsId", target = "ordersProducts")
    Products toEntity(ProductsDTO productsDTO);

    default Products fromId(Long id) {
        if (id == null) {
            return null;
        }
        Products products = new Products();
        products.setId(id);
        return products;
    }
}
