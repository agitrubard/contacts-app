package dev.agitrubard.report.service.impl;

import dev.agitrubard.AbstractUnitTest;
import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.PersonStatisticBuilder;
import dev.agitrubard.contact.port.PersonStatisticReadPort;
import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.ReportBuilder;
import dev.agitrubard.report.model.enums.ReportStatus;
import dev.agitrubard.report.model.enums.ReportType;
import dev.agitrubard.report.port.ReportSavePort;
import dev.agitrubard.report.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;

import java.time.LocalDateTime;
import java.util.List;

class AbstractReportCreatorImplTest extends AbstractUnitTest {

    AbstractReportCreatorImpl abstractReportCreatorImpl;

    @Mock
    ReportSavePort reportSavePort;

    @Mock
    AmqpTemplate amqpTemplate;

    @Mock
    PersonStatisticReadPort personStatisticReadPort;

    @BeforeEach
    void setUp() {
        this.abstractReportCreatorImpl = new PeopleStatisticsReportCreatorImpl(reportSavePort, amqpTemplate, personStatisticReadPort);
    }


    @Test
    void whenReportCreationRequested_thenAddReportToQueue() {

        // When
        Report mockReport = new ReportBuilder()
                .withValidValues()
                .withType(ReportType.PEOPLE_STATISTICS_BY_LOCATION)
                .withStatus(ReportStatus.PENDING)
                .withoutData()
                .build();
        Mockito.when(reportSavePort.save(Mockito.any(Report.class)))
                .thenReturn(mockReport);

        Mockito.doNothing()
                .when(amqpTemplate)
                .convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(Report.class));

        // Then
        abstractReportCreatorImpl.request();

        // Verify
        Mockito.verify(reportSavePort, Mockito.times(1))
                .save(Mockito.any(Report.class));

        Mockito.verify(amqpTemplate, Mockito.times(1))
                .convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(Report.class));

        Mockito.verify(personStatisticReadPort, Mockito.never())
                .findAllStatisticsByLocation();
    }

    @Test
    void whenReportCreationFailed_thenSaveReportAsFailed() {

        // When
        Report mockReport = new ReportBuilder()
                .withValidValues()
                .withoutId()
                .withType(ReportType.PEOPLE_STATISTICS_BY_LOCATION)
                .withStatus(ReportStatus.PENDING)
                .withoutData()
                .build();
        Mockito.when(reportSavePort.save(Mockito.any(Report.class)))
                .thenReturn(mockReport);

        Mockito.doThrow(new AmqpException("Failed to send message"))
                .when(amqpTemplate)
                .convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(Report.class));

        Report mockFailedReport = new ReportBuilder()
                .withValidValues()
                .withType(mockReport.getType())
                .withStatus(ReportStatus.FAILED)
                .withoutData()
                .build();
        Mockito.when(reportSavePort.save(Mockito.any(Report.class)))
                .thenReturn(mockFailedReport);

        // Then
        abstractReportCreatorImpl.request();

        // Verify
        Mockito.verify(reportSavePort, Mockito.times(2))
                .save(Mockito.any(Report.class));

        Mockito.verify(amqpTemplate, Mockito.times(1))
                .convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(Report.class));

        Mockito.verify(personStatisticReadPort, Mockito.never())
                .findAllStatisticsByLocation();
    }


    @Test
    void givenValidReport_whenReportCreationStarted_thenCreateReport() {

        // Given
        Report mockReport = new ReportBuilder()
                .withValidValues()
                .withType(ReportType.PEOPLE_STATISTICS_BY_LOCATION)
                .withStatus(ReportStatus.PENDING)
                .withoutData()
                .build();

        // When
        Report mockProcessingReport = new ReportBuilder()
                .withId(mockReport.getId())
                .withType(mockReport.getType())
                .withStatus(ReportStatus.PROCESSING)
                .withCreatedAt(mockReport.getCreatedAt())
                .withUpdatedAt(LocalDateTime.now())
                .withoutData()
                .build();
        Mockito.when(reportSavePort.save(Mockito.any(Report.class)))
                .thenReturn(mockProcessingReport);

        List<PersonStatistic> mockPersonStatistics = List.of(
                new PersonStatisticBuilder().build(),
                new PersonStatisticBuilder().build()
        );
        Mockito.when(personStatisticReadPort.findAllStatisticsByLocation())
                .thenReturn(mockPersonStatistics);

        String mockData = "{\"statistics\":%s}".formatted(JsonUtil.toString(mockPersonStatistics));
        Report mockCompletedReport = new ReportBuilder()
                .withId(mockReport.getId())
                .withType(mockReport.getType())
                .withStatus(ReportStatus.COMPLETED)
                .withCreatedAt(mockReport.getCreatedAt())
                .withUpdatedAt(LocalDateTime.now())
                .withData(mockData)
                .build();
        Mockito.when(reportSavePort.save(Mockito.any(Report.class)))
                .thenReturn(mockCompletedReport);

        // Then
        abstractReportCreatorImpl.create(mockProcessingReport);

        // Verify
        Mockito.verify(reportSavePort, Mockito.times(2))
                .save(Mockito.any(Report.class));

        Mockito.verify(personStatisticReadPort, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

    @Test
    void givenValidReport_whenReportCreationFailed_thenSaveReportAsFailed() {

        // Given
        Report mockReport = new ReportBuilder()
                .withValidValues()
                .withType(ReportType.PEOPLE_STATISTICS_BY_LOCATION)
                .withStatus(ReportStatus.PENDING)
                .withoutData()
                .build();

        // When
        Report mockProcessingReport = new ReportBuilder()
                .withId(mockReport.getId())
                .withType(mockReport.getType())
                .withStatus(ReportStatus.PROCESSING)
                .withCreatedAt(mockReport.getCreatedAt())
                .withUpdatedAt(LocalDateTime.now())
                .withoutData()
                .build();
        Mockito.when(reportSavePort.save(Mockito.any(Report.class)))
                .thenReturn(mockProcessingReport);

        Mockito.when(personStatisticReadPort.findAllStatisticsByLocation())
                .thenThrow(new RuntimeException("Failed to get statistics"));

        Report mockCompletedReport = new ReportBuilder()
                .withId(mockReport.getId())
                .withType(mockReport.getType())
                .withStatus(ReportStatus.FAILED)
                .withCreatedAt(mockReport.getCreatedAt())
                .withUpdatedAt(LocalDateTime.now())
                .withoutData()
                .build();
        Mockito.when(reportSavePort.save(Mockito.any(Report.class)))
                .thenReturn(mockCompletedReport);

        // Then
        abstractReportCreatorImpl.create(mockProcessingReport);

        // Verify
        Mockito.verify(reportSavePort, Mockito.times(2))
                .save(Mockito.any(Report.class));

        Mockito.verify(personStatisticReadPort, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

}
