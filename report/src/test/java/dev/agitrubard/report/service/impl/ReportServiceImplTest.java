package dev.agitrubard.report.service.impl;

import dev.agitrubard.report.AbstractUnitTest;
import dev.agitrubard.report.exception.ReportNotFoundException;
import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.ReportBuilder;
import dev.agitrubard.report.port.ReportReadPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class ReportServiceImplTest extends AbstractUnitTest {

    @InjectMocks
    ReportServiceImpl reportService;

    @Mock
    ReportReadPort reportReadPort;


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

}
