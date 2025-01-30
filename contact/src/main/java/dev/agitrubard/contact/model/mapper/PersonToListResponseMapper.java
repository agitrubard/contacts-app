package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.response.PersonListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonToListResponseMapper extends BaseMapper<Person, PersonListResponse> {

    PersonToListResponseMapper INSTANCE = Mappers.getMapper(PersonToListResponseMapper.class);

}
