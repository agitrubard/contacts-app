package dev.agitrubard.contact.controller;

import dev.agitrubard.contact.AbstractRestControllerTest;
import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonBuilder;
import dev.agitrubard.contact.model.request.CustomPageRequest;
import dev.agitrubard.contact.model.request.CustomPageRequestBuilder;
import dev.agitrubard.contact.model.response.CustomErrorResponse;
import dev.agitrubard.contact.model.response.CustomErrorResponseBuilder;
import dev.agitrubard.contact.model.response.CustomSuccessResponse;
import dev.agitrubard.contact.model.response.CustomSuccessResponseBuilder;
import dev.agitrubard.contact.model.response.PersonListResponse;
import dev.agitrubard.contact.service.PersonService;
import dev.agitrubard.contact.util.CustomMockMvcRequestBuilders;
import dev.agitrubard.contact.util.CustomMockResultMatchersBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Map;

class PersonControllerTest extends AbstractRestControllerTest {

    @MockitoBean
    PersonService personService;


    private static final String BASE_PATH = "/api/v1/person";

    @Test
    void givenValidCustomPageRequest_whenPeopleFound_thenReturnPersonListResponse() throws Exception {

        // Given
        CustomPageRequest mockPageRequest = new CustomPageRequestBuilder()
                .withValidValues()
                .build();

        // When
        List<Person> mockPeople = List.of(
                new PersonBuilder().withValidValues().build(),
                new PersonBuilder().withValidValues().build()
        );

        Mockito.when(personService.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(mockPeople);

        // Then
        String endpoint = BASE_PATH;
        Map<String, String> parameters = Map.of(
                "page", String.valueOf(mockPageRequest.getPage()),
                "pageSize", String.valueOf(mockPageRequest.getPageSize())
        );
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint, parameters);

        CustomSuccessResponse<List<PersonListResponse>> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contentSize()
                        .value(mockPeople.size()))
                .andExpect(CustomMockResultMatchersBuilders.contents("id")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("firstName")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("lastName")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("company")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("createdAt")
                        .isNotEmpty());

        // Verify
        Mockito.verify(personService, Mockito.times(1))
                .findAll(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void givenValidCustomPageRequest_whenPeopleNotFound_thenReturnEmptyPersonListResponse() throws Exception {

        // Given
        CustomPageRequest mockPageRequest = new CustomPageRequestBuilder()
                .withValidValues()
                .build();

        // When
        Mockito.when(personService.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(List.of());

        // Then
        String endpoint = BASE_PATH;
        Map<String, String> parameters = Map.of(
                "page", String.valueOf(mockPageRequest.getPage()),
                "pageSize", String.valueOf(mockPageRequest.getPageSize())
        );
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint, parameters);

        CustomSuccessResponse<List<PersonListResponse>> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .isEmpty())
                .andExpect(CustomMockResultMatchersBuilders.contents("id")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("firstName")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("lastName")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("company")
                        .doesNotHaveJsonPath())
                .andExpect(CustomMockResultMatchersBuilders.contents("createdAt")
                        .doesNotHaveJsonPath());

        // Verify
        Mockito.verify(personService, Mockito.times(1))
                .findAll(Mockito.anyInt(), Mockito.anyInt());
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

        // When
        Mockito.when(personService.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(List.of());

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
        Mockito.verify(personService, Mockito.never())
                .findAll(Mockito.anyInt(), Mockito.anyInt());
    }

}
