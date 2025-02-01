package dev.agitrubard.contact.controller;

import dev.agitrubard.contact.AbstractRestControllerTest;
import dev.agitrubard.contact.model.request.PersonContactAddRequest;
import dev.agitrubard.contact.model.request.PersonContactAddRequestBuilder;
import dev.agitrubard.contact.model.response.CustomErrorResponse;
import dev.agitrubard.contact.model.response.CustomErrorResponseBuilder;
import dev.agitrubard.contact.model.response.CustomSuccessResponse;
import dev.agitrubard.contact.model.response.CustomSuccessResponseBuilder;
import dev.agitrubard.contact.service.PersonContactService;
import dev.agitrubard.contact.util.CustomMockMvcRequestBuilders;
import dev.agitrubard.contact.util.CustomMockResultMatchersBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.UUID;

class PersonContactControllerTest extends AbstractRestControllerTest {

    @MockitoBean
    PersonContactService personContactService;


    private static final String BASE_PATH = "/api/v1/person";

    @Test
    void givenValidIdAndValidContactAddRequest_whenContactAdded_thenReturnSuccess() throws Exception {

        // Given
        UUID mockId = UUID.fromString("bc60985a-81aa-4be7-a942-bb71a9211bf2");
        PersonContactAddRequest mockContactAddRequest = new PersonContactAddRequestBuilder()
                .withValidValues()
                .build();

        // When
        Mockito.doNothing()
                .when(personContactService)
                .add(Mockito.any(UUID.class), Mockito.any(PersonContactAddRequest.class));

        // Then
        String endpoint = BASE_PATH + "/" + mockId + "/contact";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockContactAddRequest);

        CustomSuccessResponse<Void> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .doesNotHaveJsonPath());

        // Verify
        Mockito.verify(personContactService, Mockito.times(1))
                .add(Mockito.any(UUID.class), Mockito.any(PersonContactAddRequest.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "abc",
            "not-a-uuid",
            "g1234567-89ab-cdef-0123-456789abcdef",
            "ffffffff-ffff-ffff-ffff-fffffffffffff"
    })
    void givenIdAndContactAddRequest_whenIdDoesNotValid_thenReturnValidationError(String mockId) throws Exception {

        // Given
        PersonContactAddRequest mockContactAddRequest = new PersonContactAddRequestBuilder()
                .withValidValues()
                .build();

        // Then
        String endpoint = BASE_PATH + "/" + mockId + "/contact";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockContactAddRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(personContactService, Mockito.never())
                .add(Mockito.any(UUID.class), Mockito.any(PersonContactAddRequest.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "",
            " ",
            "a@b.c",
            "invalidemail",
            "invalidemail@",
            "12345",
            "not-an-email",
            "email@domain.",
            "email@.com",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
    })
    void givenValidIdAndContactAddRequestWithInvalidEmailAddress_whenIdDoesNotValid_thenReturnValidationError(String mockEmailAddress) throws Exception {

        // Given
        UUID mockId = UUID.fromString("7800f202-1c1b-4cd9-aa2b-fdc7b37dbcb1");
        PersonContactAddRequest mockContactAddRequest = new PersonContactAddRequestBuilder()
                .withValidValues()
                .withEmailAddress(mockEmailAddress)
                .build();

        // Then
        String endpoint = BASE_PATH + "/" + mockId + "/contact";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockContactAddRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(personContactService, Mockito.never())
                .add(Mockito.any(UUID.class), Mockito.any(PersonContactAddRequest.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "",
            " ",
            "invalidphonenumber",
            "12345",
            "not-a-phone",
            "1234567890"
    })
    void givenValidIdAndContactAddRequestWithInvalidPhoneNumber_whenIdDoesNotValid_thenReturnValidationError(String mockPhoneNumber) throws Exception {

        // Given
        UUID mockId = UUID.fromString("f64e83b7-9624-4898-a6d2-2320591a747d");
        PersonContactAddRequest mockContactAddRequest = new PersonContactAddRequestBuilder()
                .withValidValues()
                .withPhoneNumber(mockPhoneNumber)
                .build();

        // Then
        String endpoint = BASE_PATH + "/" + mockId + "/contact";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockContactAddRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(personContactService, Mockito.never())
                .add(Mockito.any(UUID.class), Mockito.any(PersonContactAddRequest.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "",
            " ",
            "1",
            "I",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    })
    void givenValidIdAndContactAddRequestWithInvalidCity_whenIdDoesNotValid_thenReturnValidationError(String mockCity) throws Exception {

        // Given
        UUID mockId = UUID.fromString("a97f47ce-1787-46d5-8417-314d751d6ed1");
        PersonContactAddRequest mockContactAddRequest = new PersonContactAddRequestBuilder()
                .withValidValues()
                .withCity(mockCity)
                .build();

        // Then
        String endpoint = BASE_PATH + "/" + mockId + "/contact";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockContactAddRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(personContactService, Mockito.never())
                .add(Mockito.any(UUID.class), Mockito.any(PersonContactAddRequest.class));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "",
            " ",
            "1",
            "I",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    })
    void givenValidIdAndContactAddRequestWithInvalidDistrict_whenIdDoesNotValid_thenReturnValidationError(String mockDistrict) throws Exception {

        // Given
        UUID mockId = UUID.fromString("a97f47ce-1787-46d5-8417-314d751d6ed1");
        PersonContactAddRequest mockContactAddRequest = new PersonContactAddRequestBuilder()
                .withValidValues()
                .withDistrict(mockDistrict)
                .build();

        // Then
        String endpoint = BASE_PATH + "/" + mockId + "/contact";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockContactAddRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(personContactService, Mockito.never())
                .add(Mockito.any(UUID.class), Mockito.any(PersonContactAddRequest.class));
    }


    @Test
    void givenValidId_whenPersonContactDeleted_thenReturnSuccess() throws Exception {

        // Given
        UUID mockId = UUID.fromString("b79b7fa1-ea11-42db-a46e-d12159f08236");

        // When
        Mockito.doNothing()
                .when(personContactService)
                .delete(mockId);

        // Then
        String endpoint = BASE_PATH + "/contact" + "/" + mockId;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .delete(endpoint);

        CustomSuccessResponse<Void> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .doesNotHaveJsonPath());

        // Verify
        Mockito.verify(personContactService, Mockito.times(1))
                .delete(Mockito.any(UUID.class));
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
        String endpoint = BASE_PATH + "/contact" + "/" + mockId;
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .delete(endpoint);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(personContactService, Mockito.never())
                .delete(Mockito.any(UUID.class));
    }

}
