package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonToResponseMapper extends BaseMapper<Person, PersonResponse> {

    PersonToResponseMapper INSTANCE = Mappers.getMapper(PersonToResponseMapper.class);

}
