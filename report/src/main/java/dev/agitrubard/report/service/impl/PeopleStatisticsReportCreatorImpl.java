package dev.agitrubard.report.service.impl;

import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.port.PersonStatisticReadPort;
import dev.agitrubard.report.model.enums.ReportType;
import dev.agitrubard.report.port.ReportSavePort;
import dev.agitrubard.report.service.ReportCreator;
import dev.agitrubard.report.util.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class PeopleStatisticsReportCreatorImpl extends AbstractReportCreatorImpl implements ReportCreator {

    private final PersonStatisticReadPort personStatisticReadPort;

    public PeopleStatisticsReportCreatorImpl(ReportSavePort reportSavePort,
                                             AmqpTemplate amqpTemplate,
                                             PersonStatisticReadPort personStatisticReadPort) {

        super(reportSavePort, amqpTemplate);
        this.personStatisticReadPort = personStatisticReadPort;
    }

    @Override
    public ReportType getType() {
        return ReportType.PEOPLE_STATISTICS_BY_LOCATION;
    }

    @Override
    public String create() {
        List<PersonStatistic> personStatistics = personStatisticReadPort.findAllStatisticsByLocation();
        return "{\"statistics\":%s}".formatted(JsonUtil.toString(personStatistics));
    }

}
