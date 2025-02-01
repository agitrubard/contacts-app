package dev.agitrubard.report.controller;

import dev.agitrubard.report.AbstractRestControllerTest;
import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.ReportBuilder;
import dev.agitrubard.report.model.enums.ReportType;
import dev.agitrubard.report.model.request.CustomPageRequest;
import dev.agitrubard.report.model.request.CustomPageRequestBuilder;
import dev.agitrubard.report.model.request.ReportCreateRequest;
import dev.agitrubard.report.model.request.ReportCreateRequestBuilder;
import dev.agitrubard.report.model.response.CustomErrorResponse;
import dev.agitrubard.report.model.response.CustomErrorResponseBuilder;
import dev.agitrubard.report.model.response.CustomSuccessResponse;
import dev.agitrubard.report.model.response.CustomSuccessResponseBuilder;
import dev.agitrubard.report.model.response.ReportListResponse;
import dev.agitrubard.report.service.ReportService;
import dev.agitrubard.report.util.CustomMockMvcRequestBuilders;
import dev.agitrubard.report.util.CustomMockResultMatchersBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

class ReportControllerTest extends AbstractRestControllerTest {

    @MockitoBean
    ReportService reportService;


    private static final String BASE_PATH = "/api/v1/report";

    @Test
    void givenValidCustomPageRequest_whenReportsFound_thenReturnReportListResponse() throws Exception {

        // Given
        CustomPageRequest mockPageRequest = new CustomPageRequestBuilder()
                .withValidValues()
                .build();

        // When
        List<Report> mockReports = List.of(
                new ReportBuilder().withValidValues().build(),
                new ReportBuilder().withValidValues().build()
        );

        Mockito.when(reportService.findAll(Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(mockReports);

        // Then
        String endpoint = BASE_PATH;
        Map<String, String> parameters = Map.of(
                "page", String.valueOf(mockPageRequest.getPage()),
                "pageSize", String.valueOf(mockPageRequest.getPageSize())
        );
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint, parameters);

        CustomSuccessResponse<List<ReportListResponse>> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contentSize()
                        .value(mockReports.size()))
                .andExpect(CustomMockResultMatchersBuilders.contents("id")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("type")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("status")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("createdAt")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("updatedAt")
                        .isNotEmpty());

        // Verify
        Mockito.verify(reportService, Mockito.times(1))
                .findAll(Mockito.any(Integer.class), Mockito.any(Integer.class));
    }

    @Test
    void givenValidCustomPageRequest_whenReportsNotFound_thenReturnEmptyReportListResponse() throws Exception {

        // Given
        CustomPageRequest mockPageRequest = new CustomPageRequestBuilder()
                .withValidValues()
                .build();

        // When
        Mockito.when(reportService.findAll(Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(List.of());

        // Then
        String endpoint = BASE_PATH;
        Map<String, String> parameters = Map.of(
                "page", String.valueOf(mockPageRequest.getPage()),
                "pageSize", String.valueOf(mockPageRequest.getPageSize())
        );
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint, parameters);

        CustomSuccessResponse<List<ReportListResponse>> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .isEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("id")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("type")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("status")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("createdAt")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("updatedAt")
                        .doesNotHaveJsonPath());

        // Verify
        Mockito.verify(reportService, Mockito.times(1))
                .findAll(Mockito.any(Integer.class), Mockito.any(Integer.class));
    }

    @ParameterizedTest
    @CsvSource({
            " , ",
            "1, ",
            " , 10",
            "0, 10",
            "0, 0",
            "1, 0",
            "1, 16",
            "1, 50",
            "1, 101",
            "1, 100",
            "1, 101"
    })
    void givenCustomPageRequest_whenPageOrPageSizeAreEmpty_thenReturnValidationError(Integer mockPage, Integer mockPageSize) throws Exception {

        // Given
        CustomPageRequest mockPageRequest = new CustomPageRequestBuilder()
                .withValidValues()
                .withPage(mockPage)
                .withPageSize(mockPageSize)
                .build();

        // Then
        String endpoint = BASE_PATH;
        Map<String, String> parameters = Map.of(
                "page", String.valueOf(mockPageRequest.getPage()),
                "pageSize", String.valueOf(mockPageRequest.getPageSize())
        );
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint, parameters);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(reportService, Mockito.never())
                .findAll(Mockito.any(Integer.class), Mockito.any(Integer.class));
    }


    @Test
    void givenValidId_whenReportFound_thenReturnReportResponse() throws Exception {

        // Given
        UUID mockId = UUID.fromString("d3fdbd2e-971f-457f-a070-c57ce8ecd81f");

        // When
        Report mockReport = new ReportBuilder()
                .withValidValues()
                .withId(mockId)
                .build();
        Mockito.when(reportService.findById(mockId))
                .thenReturn(mockReport);

        // Then
        String endpoint = BASE_PATH + "/" + mockId;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint);

        CustomSuccessResponse<Report> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("id")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("type")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("status")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("data")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("createdAt")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("updatedAt")
                        .isNotEmpty());

        // Verify
        Mockito.verify(reportService, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "abc",
            "not-a-uuid",
            "g1234567-89ab-cdef-0123-456789abcdef",
            "ffffffff-ffff-ffff-ffff-fffffffffffff"
    })
    void givenId_whenIdDoesNotValid_thenReturnValidationError(String mockId) throws Exception {

        // Then
        String endpoint = BASE_PATH + "/" + mockId;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(reportService, Mockito.never())
                .findById(Mockito.any(UUID.class));
    }


    @Test
    void givenValidReportCreateRequest_whenReportCreated_thenReturnSuccess() throws Exception {

        // Given
        ReportCreateRequest mockCreateRequest = new ReportCreateRequestBuilder()
                .build();

        // When
        Mockito.doNothing()
                .when(reportService)
                .create(Mockito.any(ReportType.class));

        // Then
        String endpoint = BASE_PATH;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockCreateRequest);

        CustomSuccessResponse<Void> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .doesNotHaveJsonPath());

        // Verify
        Mockito.verify(reportService, Mockito.times(1))
                .create(Mockito.any(ReportType.class));
    }

    @ParameterizedTest
    @NullSource
    void givenReportCreateRequest_whenTypeDoesNotValid_thenReturnValidationError(ReportType mockType) throws Exception {

        // Given
        ReportCreateRequest mockCreateRequest = new ReportCreateRequestBuilder()
                .withType(mockType)
                .build();

        // Then
        String endpoint = BASE_PATH;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockCreateRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(reportService, Mockito.never())
                .create(Mockito.any(ReportType.class));
    }

}
