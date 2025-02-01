package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.PersonContact;
import dev.agitrubard.contact.model.request.PersonContactAddRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonContactAddRequestToDomainMapper extends BaseMapper<PersonContactAddRequest, PersonContact> {

    PersonContactAddRequestToDomainMapper INSTANCE = Mappers.getMapper(PersonContactAddRequestToDomainMapper.class);

}
