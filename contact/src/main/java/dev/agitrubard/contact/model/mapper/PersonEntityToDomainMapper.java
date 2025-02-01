package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonContact;
import dev.agitrubard.contact.model.entity.PersonContactEntity;
import dev.agitrubard.contact.model.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonEntityToDomainMapper extends BaseMapper<PersonEntity, Person> {

    PersonEntityToDomainMapper INSTANCE = Mappers.getMapper(PersonEntityToDomainMapper.class);

    @Mapping(target = "person", ignore = true)
    PersonContact map(PersonContactEntity source);

}
