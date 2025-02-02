package dev.agitrubard.contact.port;

import dev.agitrubard.contact.model.PersonStatistic;

import java.util.List;

public interface PersonStatisticReadPort {

    List<PersonStatistic> findAllStatisticsByLocation();

}
