package dev.agitrubard.contact.model.mapper;

import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.response.PersonStatisticResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonStatisticToResponseMapper extends BaseMapper<PersonStatistic, PersonStatisticResponse> {

    PersonStatisticToResponseMapper INSTANCE = Mappers.getMapper(PersonStatisticToResponseMapper.class);

}
