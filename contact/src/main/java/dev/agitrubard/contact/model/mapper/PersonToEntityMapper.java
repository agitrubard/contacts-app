package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonContact;
import dev.agitrubard.contact.model.entity.PersonContactEntity;
import dev.agitrubard.contact.model.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonToEntityMapper extends BaseMapper<Person, PersonEntity> {

    PersonToEntityMapper INSTANCE = Mappers.getMapper(PersonToEntityMapper.class);

    @Mapping(target = "person", ignore = true)
    PersonContactEntity map(PersonContact source);

}
