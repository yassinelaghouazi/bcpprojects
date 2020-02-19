package ma.jconsulting.applications.btpproject.service.mapper;


import ma.jconsulting.applications.btpproject.domain.*;
import ma.jconsulting.applications.btpproject.service.dto.CompanyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Company} and its DTO {@link CompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {BopportunityMapper.class, ProjectMapper.class})
public interface CompanyMapper extends EntityMapper<CompanyDTO, Company> {

    @Mapping(source = "bopportunity.id", target = "bopportunityId")
    @Mapping(source = "project.id", target = "projectId")
    CompanyDTO toDto(Company company);

    @Mapping(source = "bopportunityId", target = "bopportunity")
    @Mapping(source = "projectId", target = "project")
    Company toEntity(CompanyDTO companyDTO);

    default Company fromId(Long id) {
        if (id == null) {
            return null;
        }
        Company company = new Company();
        company.setId(id);
        return company;
    }
}
