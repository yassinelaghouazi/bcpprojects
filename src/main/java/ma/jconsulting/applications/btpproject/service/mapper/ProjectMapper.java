package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.ProjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {CautionMapper.class, DeliveryMapper.class, OrdersMapper.class, ReglementMapper.class})
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {

    @Mapping(source = "caution.id", target = "cautionId")
    @Mapping(source = "delivery.id", target = "deliveryId")
    @Mapping(source = "orders.id", target = "ordersId")
    @Mapping(source = "reglement.id", target = "reglementId")
    ProjectDTO toDto(Project project);

    @Mapping(target = "companies", ignore = true)
    @Mapping(target = "removeCompany", ignore = true)
    @Mapping(target = "bopportuniys", ignore = true)
    @Mapping(target = "removeBopportuniy", ignore = true)
    @Mapping(source = "cautionId", target = "caution")
    @Mapping(source = "deliveryId", target = "delivery")
    @Mapping(source = "ordersId", target = "orders")
    @Mapping(source = "reglementId", target = "reglement")
    Project toEntity(ProjectDTO projectDTO);

    default Project fromId(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }
}
