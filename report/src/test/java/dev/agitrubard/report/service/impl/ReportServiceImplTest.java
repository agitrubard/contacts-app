package dev.agitrubard.report.service.impl;

import dev.agitrubard.AbstractUnitTest;
import dev.agitrubard.report.exception.ReportNotFoundException;
import dev.agitrubard.report.exception.ReportTypeNotImplementedException;
import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.ReportBuilder;
import dev.agitrubard.report.model.enums.ReportType;
import dev.agitrubard.report.port.ReportReadPort;
import dev.agitrubard.report.port.ReportSavePort;
import dev.agitrubard.report.service.ReportCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class ReportServiceImplTest extends AbstractUnitTest {

    ReportServiceImpl reportService;

    @Mock
    ReportReadPort reportReadPort;

    @Mock
    ReportSavePort reportSavePort;

    @Mock
    PeopleStatisticsReportCreatorImpl peopleStatisticsReportCreatorImpl;

    @BeforeEach
    void setUp() {

        List<ReportCreator> reportCreators = List.of(
                peopleStatisticsReportCreatorImpl
        );

        this.reportService = new ReportServiceImpl(
                this.reportReadPort,
                this.reportSavePort,
                reportCreators
        );
    }


    @Test
    void givenValidPageAndPageSize_whenReportsFound_thenReturnReports() {

        // Given
        Integer mockPage = 1;
        Integer mockPageSize = 10;

        // When
        List<Report> mockReports = List.of(
                new ReportBuilder().withValidValues().build(),
                new ReportBuilder().withValidValues().build()
        );

        Mockito.when(reportReadPort.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(mockReports);

        // Then
        List<Report> reports = reportService.findAll(mockPage, mockPageSize);

        Assertions.assertEquals(mockReports.size(), reports.size());

        // Verify
        Mockito.verify(reportReadPort, Mockito.times(1))
                .findAll(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void givenValidPageAndPageSize_whenReportsNotFound_thenReturnEmptyReports() {

        // Given
        Integer mockPage = 1;
        Integer mockPageSize = 10;

        // When
        Mockito.when(reportReadPort.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(List.of());

        // Then
        List<Report> reports = reportService.findAll(mockPage, mockPageSize);

        Assertions.assertEquals(0, reports.size());

        // Verify
        Mockito.verify(reportReadPort, Mockito.times(1))
                .findAll(Mockito.anyInt(), Mockito.anyInt());
    }


    @Test
    void givenValidId_whenReportFound_thenReturnReport() {

        // Given
        UUID mockId = UUID.fromString("b6dfab9e-461f-4bcb-b9f5-b3b75bdaa4bf");

        // When
        Report mockReport = new ReportBuilder()
                .withValidValues()
                .withId(mockId)
                .build();
        Mockito.when(reportReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(mockReport));

        // Then
        Report report = reportService.findById(mockId);

        Assertions.assertEquals(mockReport, report);

        // Verify
        Mockito.verify(reportReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
    }

    @Test
    void givenValidId_whenReportNotFound_thenThrowReportNotFoundException() {

        // Given
        UUID mockId = UUID.fromString("883af77d-6e8c-48ab-bbea-7be21168442e");

        // When
        Mockito.when(reportReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(
                ReportNotFoundException.class,
                () -> reportService.findById(mockId)
        );

        // Verify
        Mockito.verify(reportReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
    }


    @Test
    void givenValidReportType_whenReportCreatorFound_thenCreateReport() {

        // Given
        ReportType mockType = ReportType.PEOPLE_STATISTICS_BY_LOCATION;

        // When
        Mockito.when(peopleStatisticsReportCreatorImpl.getType())
                .thenReturn(ReportType.PEOPLE_STATISTICS_BY_LOCATION);

        String mockData = """
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
        Mockito.when(peopleStatisticsReportCreatorImpl.create())
                .thenReturn(mockData);

        Report mockReport = new ReportBuilder()
                .withValidValues()
                .withType(mockType)
                .withData(mockData)
                .build();
        Mockito.when(reportSavePort.save(Mockito.any(Report.class)))
                .thenReturn(mockReport);

        // Then
        reportService.create(mockType);

        // Verify
        Mockito.verify(peopleStatisticsReportCreatorImpl, Mockito.times(1))
                .getType();

        Mockito.verify(peopleStatisticsReportCreatorImpl, Mockito.times(1))
                .create();

        Mockito.verify(reportSavePort, Mockito.times(2))
                .save(Mockito.any(Report.class));
    }

    @Test
    void givenReportTypeAsNull_whenReportCreatorDoesNotFound_thenThrowReportTypeNotImplementedException() {

        // Given
        ReportType mockType = null;

        // When
        Mockito.when(peopleStatisticsReportCreatorImpl.getType())
                .thenReturn(ReportType.PEOPLE_STATISTICS_BY_LOCATION);

        // Then
        Assertions.assertThrows(
                ReportTypeNotImplementedException.class,
                () -> reportService.create(mockType)
        );

        // Verify
        Mockito.verify(peopleStatisticsReportCreatorImpl, Mockito.times(1))
                .getType();

        Mockito.verify(peopleStatisticsReportCreatorImpl, Mockito.never())
                .create();

        Mockito.verify(reportSavePort, Mockito.times(1))
                .save(Mockito.any(Report.class));
    }

}
