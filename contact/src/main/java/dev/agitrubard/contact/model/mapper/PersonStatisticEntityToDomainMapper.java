package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.entity.PersonStatisticEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonStatisticEntityToDomainMapper extends BaseMapper<PersonStatisticEntity, PersonStatistic> {

    PersonStatisticEntityToDomainMapper INSTANCE = Mappers.getMapper(PersonStatisticEntityToDomainMapper.class);

}
