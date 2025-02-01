package dev.agitrubard.contact.port.adapter;

import dev.agitrubard.contact.client.PersonStatisticFeignClient;
import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.mapper.PersonStatisticResponseToDomainMapper;
import dev.agitrubard.contact.model.response.PersonStatisticResponse;
import dev.agitrubard.contact.port.PersonStatisticReadPort;
import dev.agitrubard.report.model.response.CustomSuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class PersonStatisticAdapter implements PersonStatisticReadPort {

    private final PersonStatisticFeignClient personStatisticFeignClient;


    private final PersonStatisticResponseToDomainMapper personStatisticResponseToDomainMapper = PersonStatisticResponseToDomainMapper.INSTANCE;


    @Override
    public List<PersonStatistic> findAllStatisticsByLocation() {

        CustomSuccessResponse<List<PersonStatisticResponse>> response = personStatisticFeignClient
                .findAllStatisticsByLocation();

        List<PersonStatisticResponse> personStatisticResponses = response.getContent();

        return personStatisticResponseToDomainMapper.map(personStatisticResponses);
    }

}
