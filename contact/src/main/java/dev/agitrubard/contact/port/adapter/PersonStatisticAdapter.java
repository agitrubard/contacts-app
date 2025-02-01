package dev.agitrubard.contact.port.adapter;

import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.entity.PersonStatisticEntity;
import dev.agitrubard.contact.model.mapper.PersonStatisticEntityToDomainMapper;
import dev.agitrubard.contact.port.PersonStatisticReadPort;
import dev.agitrubard.contact.repository.PersonStatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class PersonStatisticAdapter implements PersonStatisticReadPort {

    private final PersonStatisticRepository personStatisticRepository;


    private final PersonStatisticEntityToDomainMapper personStatisticEntityToDomainMapper = PersonStatisticEntityToDomainMapper.INSTANCE;


    @Override
    public List<PersonStatistic> findAllStatisticsByLocation() {
        List<PersonStatisticEntity> personStatisticEntities = personStatisticRepository.findAllStatisticsByLocation();
        return personStatisticEntityToDomainMapper.map(personStatisticEntities);
    }

}
