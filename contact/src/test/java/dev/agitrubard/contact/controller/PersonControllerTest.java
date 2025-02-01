package dev.agitrubard.contact.controller;

import dev.agitrubard.contact.AbstractRestControllerTest;
import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonBuilder;
import dev.agitrubard.contact.model.request.CustomPageRequest;
import dev.agitrubard.contact.model.request.CustomPageRequestBuilder;
import dev.agitrubard.contact.model.request.PersonCreateRequest;
import dev.agitrubard.contact.model.request.PersonCreateRequestBuilder;
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
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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


    @Test
    void givenValidId_whenPersonFound_thenReturnPersonResponse() throws Exception {

        // Given
        UUID mockId = UUID.fromString("4fa4e388-fd5f-461d-8728-412cf6431d82");

        // When
        Person mockPerson = new PersonBuilder()
                .withValidValues()
                .withId(mockId)
                .build();
        Mockito.when(personService.findById(mockId))
                .thenReturn(mockPerson);

        // Then
        String endpoint = BASE_PATH + "/" + mockId;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .get(endpoint);

        CustomSuccessResponse<Person> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("id")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("firstName")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("lastName")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("company")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("createdAt")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("updatedAt")
                        .doesNotExist())
                .andExpect(CustomMockResultMatchersBuilders.content("contacts")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("contacts[*].id")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("contacts[*].emailAddress")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("contacts[*].phoneNumber")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("contacts[*].city")
                        .isNotEmpty())
                .andExpect(CustomMockResultMatchersBuilders.content("contacts[*].district")
                        .isNotEmpty());

        // Verify
        Mockito.verify(personService, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
    }

    @ParameterizedTest
    @CsvSource({
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
        Mockito.verify(personService, Mockito.never())
                .findById(Mockito.any(UUID.class));
    }


    @Test
    void givenValidPersonCreateRequest_whenPersonCreated_thenReturnSuccess() throws Exception {

        // Given
        PersonCreateRequest mockCreateRequest = new PersonCreateRequestBuilder()
                .build();

        // When
        Mockito.doNothing()
                .when(personService)
                .create(Mockito.any(PersonCreateRequest.class));

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
        Mockito.verify(personService, Mockito.times(1))
                .create(Mockito.any(PersonCreateRequest.class));
    }


    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            " ",
            "a",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    })
    void givenPersonCreateRequest_whenFirstNameDoesNotValid_thenReturnValidationError(String mockFirstName) throws Exception {

        // Given
        PersonCreateRequest mockCreateRequest = new PersonCreateRequestBuilder()
                .withFirstName(mockFirstName)
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
        Mockito.verify(personService, Mockito.never())
                .create(Mockito.any(PersonCreateRequest.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            " ",
            "a",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    })
    void givenPersonCreateRequest_whenLastNameDoesNotValid_thenReturnValidationError(String mockLastName) throws Exception {

        // Given
        PersonCreateRequest mockCreateRequest = new PersonCreateRequestBuilder()
                .withLastName(mockLastName)
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
        Mockito.verify(personService, Mockito.never())
                .create(Mockito.any(PersonCreateRequest.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            " ",
            "a",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    })
    void givenPersonCreateRequest_whenCompanyDoesNotValid_thenReturnValidationError(String mockCompany) throws Exception {

        // Given
        PersonCreateRequest mockCreateRequest = new PersonCreateRequestBuilder()
                .withCompany(mockCompany)
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
        Mockito.verify(personService, Mockito.never())
                .create(Mockito.any(PersonCreateRequest.class));
    }


    @Test
    void givenValidId_whenPersonDeleted_thenReturnSuccess() throws Exception {

        // Given
        UUID mockId = UUID.fromString("4f08ef84-45a8-47a6-8c38-54b378465365");

        // When
        Mockito.doNothing()
                .when(personService)
                .delete(mockId);

        // Then
        String endpoint = BASE_PATH + "/" + mockId;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .delete(endpoint);

        CustomSuccessResponse<Void> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .doesNotHaveJsonPath());

        // Verify
        Mockito.verify(personService, Mockito.times(1))
                .delete(Mockito.any(UUID.class));
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "abc",
            "not-a-uuid",
            "g1234567-89ab-cdef-0123-456789abcdef",
            "ffffffff-ffff-ffff-ffff-fffffffffffff"
    })
    void givenIdForDeleting_whenIdDoesNotValid_thenReturnValidationError(String mockId) throws Exception {

        // Then
        String endpoint = BASE_PATH + "/" + mockId;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .delete(endpoint);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(personService, Mockito.never())
                .delete(Mockito.any(UUID.class));
    }

}
