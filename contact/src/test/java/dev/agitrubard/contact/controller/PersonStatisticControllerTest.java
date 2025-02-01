package dev.agitrubard.contact.controller;

import dev.agitrubard.contact.AbstractRestControllerTest;
import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.PersonStatisticBuilder;
import dev.agitrubard.contact.model.response.CustomSuccessResponse;
import dev.agitrubard.contact.model.response.CustomSuccessResponseBuilder;
import dev.agitrubard.contact.model.response.PersonStatisticResponse;
import dev.agitrubard.contact.service.PersonStatisticService;
import dev.agitrubard.contact.util.CustomMockMvcRequestBuilders;
import dev.agitrubard.contact.util.CustomMockResultMatchersBuilders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

class PersonStatisticControllerTest extends AbstractRestControllerTest {

    @MockitoBean
    PersonStatisticService personStatisticService;


    private static final String BASE_PATH = "/api/v1/person/statistic";


    @Test
    void whenFoundAllStatisticByLocation_thenReturnPersonStatisticResponses() throws Exception {

        // When
        List<PersonStatistic> mockPersonStatistics = List.of(
                new PersonStatisticBuilder().build(),
                new PersonStatisticBuilder().build()
        );
        Mockito.when(personStatisticService.findAllStatisticsByLocation())
                .thenReturn(mockPersonStatistics);

        // Then
        String endpoint = BASE_PATH;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint);

        CustomSuccessResponse<List<PersonStatisticResponse>> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contentSize()
                        .value(mockPersonStatistics.size()))
                .andExpect(CustomMockResultMatchersBuilders.contents("city")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("district")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("personCount")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("phoneNumberCount")
                        .isNotEmpty());

        // Verify
        Mockito.verify(personStatisticService, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

    @Test
    void whenDidNotFindAllStatisticByLocation_thenReturnEmptyPersonStatisticResponses() throws Exception {

        // When
        Mockito.when(personStatisticService.findAllStatisticsByLocation())
                .thenReturn(List.of());

        // Then
        String endpoint = BASE_PATH;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint);

        CustomSuccessResponse<List<PersonStatisticResponse>> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .isEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contentSize()
                        .value(0))
                .andExpect(CustomMockResultMatchersBuilders.contents("city")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("district")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("personCount")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("phoneNumberCount")
                        .doesNotHaveJsonPath());

        // Verify
        Mockito.verify(personStatisticService, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

}
