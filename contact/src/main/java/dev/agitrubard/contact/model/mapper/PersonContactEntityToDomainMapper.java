package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonContact;
import dev.agitrubard.contact.model.entity.PersonContactEntity;
import dev.agitrubard.contact.model.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonContactEntityToDomainMapper extends BaseMapper<PersonContactEntity, PersonContact> {

    PersonContactEntityToDomainMapper INSTANCE = Mappers.getMapper(PersonContactEntityToDomainMapper.class);

    @Mapping(target = "contacts", ignore = true)
    Person map(PersonEntity source);

}
