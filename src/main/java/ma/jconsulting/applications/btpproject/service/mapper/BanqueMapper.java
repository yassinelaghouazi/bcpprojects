package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.BanqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Banque} and its DTO {@link BanqueDTO}.
 */
@Mapper(componentModel = "spring", uses = {CautionMapper.class})
public interface BanqueMapper extends EntityMapper<BanqueDTO, Banque> {

    @Mapping(source = "caution.id", target = "cautionId")
    BanqueDTO toDto(Banque banque);

    @Mapping(source = "cautionId", target = "caution")
    Banque toEntity(BanqueDTO banqueDTO);

    default Banque fromId(Long id) {
        if (id == null) {
            return null;
        }
        Banque banque = new Banque();
        banque.setId(id);
        return banque;
    }
}
