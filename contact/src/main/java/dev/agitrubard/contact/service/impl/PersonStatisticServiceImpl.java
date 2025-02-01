package dev.agitrubard.contact.service.impl;

import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.port.PersonStatisticReadPort;
import dev.agitrubard.contact.service.PersonStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class PersonStatisticServiceImpl implements PersonStatisticService {

    private final PersonStatisticReadPort personStatisticReadPort;


    @Override
    public List<PersonStatistic> findAllStatisticsByLocation() {
        return personStatisticReadPort.findAllStatisticsByLocation();
    }

}
