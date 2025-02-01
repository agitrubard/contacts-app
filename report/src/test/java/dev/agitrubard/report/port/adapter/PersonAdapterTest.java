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
import java.util.Optional;
import java.util.UUID;

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


    @Test
    void givenValidId_whenReportFound_thenReturnReport() {

        // Given
        UUID mockId = UUID.fromString("00352c56-3326-43bb-9454-a732fa66e5e9");

        // When
        ReportEntity mockReportEntity = new ReportEntityBuilder()
                .withValidValues()
                .withId(mockId)
                .build();
        Mockito.when(reportRepository.findById(mockId))
                .thenReturn(Optional.of(mockReportEntity));

        // Then
        Optional<Report> report = reportAdapter.findById(mockId);

        Assertions.assertTrue(report.isPresent());

        // Verify
        Mockito.verify(reportRepository, Mockito.times(1))
                .findById(mockId);
    }

    @Test
    void givenValidId_whenReportNotFound_thenReturnEmpty() {

        // Given
        UUID mockId = UUID.fromString("2c3bf1cb-ad20-4ada-851b-39636bc2d14c");

        // When
        Mockito.when(reportRepository.findById(mockId))
                .thenReturn(Optional.empty());

        // Then
        Optional<Report> report = reportAdapter.findById(mockId);

        Assertions.assertTrue(report.isEmpty());

        // Verify
        Mockito.verify(reportRepository, Mockito.times(1))
                .findById(mockId);
    }

}
