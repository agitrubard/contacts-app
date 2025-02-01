package dev.agitrubard.contact.service;

import dev.agitrubard.contact.model.PersonStatistic;

import java.util.List;

public interface PersonStatisticService {

    List<PersonStatistic> findAllStatisticsByLocation();

}
