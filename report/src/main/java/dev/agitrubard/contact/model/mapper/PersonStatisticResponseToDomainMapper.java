package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.response.PersonStatisticResponse;
import dev.agitrubard.report.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonStatisticResponseToDomainMapper extends BaseMapper<PersonStatisticResponse, PersonStatistic> {

    PersonStatisticResponseToDomainMapper INSTANCE = Mappers.getMapper(PersonStatisticResponseToDomainMapper.class);

}
