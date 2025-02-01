package dev.agitrubard.report.service.impl;

import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.PersonStatisticBuilder;
import dev.agitrubard.contact.port.PersonStatisticReadPort;
import dev.agitrubard.report.AbstractUnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

class PeopleStatisticsReportCreatorImplTest extends AbstractUnitTest {

    @InjectMocks
    PeopleStatisticsReportCreatorImpl peopleStatisticsReportCreator;

    @Mock
    PersonStatisticReadPort personStatisticReadPort;


    @Test
    void whenReportCreatedSuccessfully_thenReturnReportData() {

        // When
        List<PersonStatistic> personStatistics = List.of(
                new PersonStatisticBuilder().build(),
                new PersonStatisticBuilder().build()
        );
        Mockito.when(personStatisticReadPort.findAllStatisticsByLocation())
                .thenReturn(personStatistics);

        // Then
        String reportData = peopleStatisticsReportCreator.create();

        Assertions.assertNotNull(reportData);
        Assertions.assertTrue(reportData.contains("{\"statistics\":"));

        // Verify
        Mockito.verify(personStatisticReadPort)
                .findAllStatisticsByLocation();

    }

}
