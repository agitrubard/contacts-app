package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.request.PersonCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonCreateRequestToDomainMapper extends BaseMapper<PersonCreateRequest, Person> {

    PersonCreateRequestToDomainMapper INSTANCE = Mappers.getMapper(PersonCreateRequestToDomainMapper.class);

}
