package dev.agitrubard.report.port.adapter;

import dev.agitrubard.report.AbstractUnitTest;
import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.entity.ReportEntity;
import dev.agitrubard.report.model.entity.ReportEntityBuilder;
import dev.agitrubard.report.repository.ReportRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

class ReportAdapterTest extends AbstractUnitTest {

    @InjectMocks
    ReportAdapter reportAdapter;

    @Mock
    ReportRepository reportRepository;


    @Test
    void givenValidPageAndPageSize_whenReportsFound_thenReturnReports() {

        // Given
        Integer mockPage = 1;
        Integer mockPageSize = 10;

        // When
        Pageable mockPageable = PageRequest.of(mockPage - 1, mockPageSize);

        List<ReportEntity> mockReportEntities = List.of(
                new ReportEntityBuilder().withValidValues().build(),
                new ReportEntityBuilder().withValidValues().build()
        );
        Page<ReportEntity> mockReportEntitiesPage = new PageImpl<>(mockReportEntities);
        Mockito.when(reportRepository.findAll(mockPageable))
                .thenReturn(mockReportEntitiesPage);

        // Then
        List<Report> reports = reportAdapter.findAll(mockPage, mockPageSize);

        Assertions.assertEquals(mockReportEntities.size(), reports.size());

        // Verify
        Mockito.verify(reportRepository, Mockito.times(1))
                .findAll(mockPageable);
    }

    @Test
    void givenValidPageAndPageSize_whenReportsNotFound_thenReturnEmptyReports() {

        // Given
        Integer mockPage = 1;
        Integer mockPageSize = 10;

        // When
        Pageable mockPageable = PageRequest.of(mockPage - 1, mockPageSize);
        Mockito.when(reportRepository.findAll(mockPageable))
                .thenReturn(Page.empty());

        // Then
        List<Report> reports = reportAdapter.findAll(mockPage, mockPageSize);

        Assertions.assertEquals(0, reports.size());

        // Verify
        Mockito.verify(reportRepository, Mockito.times(1))
                .findAll(mockPageable);
    }

}
