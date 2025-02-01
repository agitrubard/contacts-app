package dev.agitrubard.report.service.impl;

import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.port.PersonStatisticReadPort;
import dev.agitrubard.report.model.enums.ReportType;
import dev.agitrubard.report.service.ReportCreator;
import dev.agitrubard.report.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class PeopleStatisticsReportCreatorImpl implements ReportCreator {

    private final PersonStatisticReadPort personStatisticReadPort;


    @Override
    public ReportType getType() {
        return ReportType.PEOPLE_STATISTICS_BY_LOCATION;
    }

    @Override
    public String create() {

        log.info("Creating People Statistics Report...");

        List<PersonStatistic> personStatistics = personStatisticReadPort.findAllStatisticsByLocation();

        log.info("People Statistics Report created successfully!");

        return "{\"statistics\":%s}".formatted(JsonUtil.toString(personStatistics));
    }

}
