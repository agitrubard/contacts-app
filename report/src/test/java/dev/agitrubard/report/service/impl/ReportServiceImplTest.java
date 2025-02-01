package dev.agitrubard.report.service.impl;

import dev.agitrubard.report.AbstractUnitTest;
import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.ReportBuilder;
import dev.agitrubard.report.port.ReportReadPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

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

}
