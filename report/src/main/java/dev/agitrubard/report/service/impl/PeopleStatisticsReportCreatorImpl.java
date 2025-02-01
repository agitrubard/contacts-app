package dev.agitrubard.report.service.impl;

import dev.agitrubard.report.model.enums.ReportType;
import dev.agitrubard.report.service.ReportCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class PeopleStatisticsReportCreatorImpl implements ReportCreator {

    @Override
    public ReportType getType() {
        return ReportType.PEOPLE_STATISTICS_BY_LOCATION;
    }

    @Override
    public String create() {
        return """
                {
                    "statistics": [
                        {
                            "city": "İstanbul",
                            "district": "Kadıköy",
                            "peopleCount": 12,
                            "phoneNumbersCount": 9
                        },
                        {
                            "city": "Ankara",
                            "district": "Çankaya",
                            "peopleCount": 3,
                            "phoneNumbersCount": 26
                        }
                    ]
                }
                """;
    }

}
