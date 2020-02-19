package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.ProviderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Provider} and its DTO {@link ProviderDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrdersMapper.class, ProductsMapper.class, ReglementMapper.class})
public interface ProviderMapper extends EntityMapper<ProviderDTO, Provider> {

    @Mapping(source = "orders.id", target = "ordersId")
    @Mapping(source = "products.id", target = "productsId")
    @Mapping(source = "reglement.id", target = "reglementId")
    ProviderDTO toDto(Provider provider);

    @Mapping(source = "ordersId", target = "orders")
    @Mapping(source = "productsId", target = "products")
    @Mapping(source = "reglementId", target = "reglement")
    Provider toEntity(ProviderDTO providerDTO);

    default Provider fromId(Long id) {
        if (id == null) {
            return null;
        }
        Provider provider = new Provider();
        provider.setId(id);
        return provider;
    }
}
