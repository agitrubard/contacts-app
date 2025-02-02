package dev.agitrubard.report.exception.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import dev.agitrubard.AbstractUnitTest;
import dev.agitrubard.report.exception.AbstractNotFoundException;
import dev.agitrubard.report.model.response.CustomErrorResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.Serial;
import java.lang.reflect.Method;
import java.sql.SQLException;

class GlobalExceptionHandlerTest extends AbstractUnitTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


    @Test
    void givenJsonParseError_whenThrowHttpMessageNotReadableException_thenReturnError() {

        // Given
        HttpInputMessage mockHttpInputMessage = new MockHttpInputMessage(new byte[]{});
        HttpMessageNotReadableException mockException = new HttpMessageNotReadableException("Invalid JSON", mockHttpInputMessage);

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handleJsonParseErrors(mockException);
        this.checkFields(mockErrorResponse, errorResponse);
    }

    @Test
    @SuppressWarnings("deprecation")
    void givenInvalidJsonFormat_whenThrowHttpMessageNotReadableException_thenReturnCustomErrorResponse() {

        // Given
        InvalidFormatException mockInvalidFormatException = InvalidFormatException.from(null, "Invalid format", null, String.class);
        JsonMappingException.Reference mockReference = new JsonMappingException.Reference("testObject", "testField");
        mockInvalidFormatException.prependPath(mockReference);
        HttpMessageNotReadableException mockException = new HttpMessageNotReadableException("Invalid JSON", mockInvalidFormatException);

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handleJsonParseErrors(mockException);
        this.checkFields(mockErrorResponse, errorResponse);
    }


    @Test
    @SuppressWarnings("ConstantConditions")
    void givenInvalidArgumentToAnyEndpoint_whenThrowMethodArgumentTypeMismatchException_thenReturnError() {
        // Given
        MethodArgumentTypeMismatchException mockException = new MethodArgumentTypeMismatchException("test", String.class, "username", null, null);

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handleValidationErrors(mockException);
        this.checkFields(mockErrorResponse, errorResponse);

    }

    @Test
    void givenInvalidArgumentToAnyEndpoint_whenThrowMethodArgumentNotValidException_thenReturnError() throws NoSuchMethodException {

        // Given
        MethodArgumentNotValidException mockException = this.getMethodArgumentNotValidException();

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handleValidationErrors(mockException);
        this.checkFields(mockErrorResponse, errorResponse);
    }

    private @NotNull MethodArgumentNotValidException getMethodArgumentNotValidException() throws NoSuchMethodException {
        Method method = GlobalExceptionHandlerTest.class.getDeclaredMethod("givenInvalidArgumentToAnyEndpoint_whenThrowMethodArgumentNotValidException_thenReturnError");
        int parameterIndex = -1;

        MethodParameter mockParameter = new MethodParameter(method, parameterIndex);
        BindingResult mockBindingResult = new BeanPropertyBindingResult(null, "");
        return new MethodArgumentNotValidException(mockParameter, mockBindingResult);
    }


    @Test
    void givenInvalidArgumentToAnyEndpoint_whenThrowConstraintViolationException_thenReturnError() {

        // Given
        ConstraintViolationException mockException = new ConstraintViolationException(null);

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handlePathVariableErrors(mockException);
        this.checkFields(mockErrorResponse, errorResponse);
    }


    @Test
    void givenResourceNotFound_whenThrowAbstractNotFoundException_thenReturnError() {

        // Given
        AbstractNotFoundException mockException = new AbstractNotFoundException("Resource not found") {

            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public String getMessage() {
                return "Resource not found";
            }
        };

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.NOT_FOUND_ERROR.getName())
                .message(mockException.getMessage())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handleNotFoundError(mockException);
        this.checkFields(mockErrorResponse, errorResponse);
    }


    @Test
    void givenNullPointerException_whenThrowNullPointerException_thenReturnError() {

        // Given
        NullPointerException mockException = new NullPointerException();

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.PROCESS_ERROR.getName())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handleProcessError(mockException);
        this.checkFields(mockErrorResponse, errorResponse);
    }


    @Test
    void givenHandleEndpointNotFoundException_whenThrowNoResourceFoundException_thenReturnError() {

        // Given
        HttpMethod[] httpMethods = HttpMethod.values();

        for (HttpMethod method : httpMethods) {
            NoResourceFoundException mockException = new NoResourceFoundException(method, "Resource not found");

            // When
            CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                    .header(CustomErrorResponse.Header.API_ERROR.getName())
                    .build();

            // Then
            CustomErrorResponse errorResponse = globalExceptionHandler.handleEndpointNotFoundError(mockException);
            this.checkFields(mockErrorResponse, errorResponse);
        }
    }


    @Test
    void givenSQLException_whenThrowSQLException_thenReturnError() {

        // Given
        SQLException mockException = new SQLException("Database error");

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.DATABASE_ERROR.getName())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handleSQLError(mockException);
        this.checkFields(mockErrorResponse, errorResponse);
    }


    @Test
    void givenUnsupportedHttpMethod_whenThrowHttpRequestMethodNotSupportedException_thenReturnError() {

        // Given
        HttpRequestMethodNotSupportedException mockException = new HttpRequestMethodNotSupportedException("Unsupported method");

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.API_ERROR.getName())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handleHttpRequestMethodNotSupportedException(mockException);
        this.checkFields(mockErrorResponse, errorResponse);
    }


    @Test
    void givenDataAccessException_whenThrowDataAccessException_thenReturnError() {

        // Given
        DataAccessException mockException = new DataAccessException("Data access error") {

            @Serial
            private static final long serialVersionUID = 1L;
        };

        // When
        CustomErrorResponse mockErrorResponse = CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.DATABASE_ERROR.getName())
                .build();

        // Then
        CustomErrorResponse errorResponse = globalExceptionHandler.handleDataAccessException(mockException);
        this.checkFields(mockErrorResponse, errorResponse);
    }


    private void checkFields(CustomErrorResponse mockErrorResponse, CustomErrorResponse errorResponse) {

        Assertions.assertNotNull(errorResponse.getTime());
        Assertions.assertEquals(mockErrorResponse.getHeader(), errorResponse.getHeader());
        Assertions.assertEquals(mockErrorResponse.getIsSuccess(), errorResponse.getIsSuccess());

        if (mockErrorResponse.getMessage() != null) {
            Assertions.assertEquals(mockErrorResponse.getMessage(), errorResponse.getMessage());
        }

    }

}