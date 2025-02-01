package dev.agitrubard.report.exception.handler;

import dev.agitrubard.report.exception.AbstractNotFoundException;
import dev.agitrubard.report.model.response.CustomErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    CustomErrorResponse handleJsonParseErrors(final HttpMessageNotReadableException exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    CustomErrorResponse handleValidationErrors(final MethodArgumentTypeMismatchException exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    CustomErrorResponse handleValidationErrors(final MethodArgumentNotValidException exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    CustomErrorResponse handlePathVariableErrors(final ConstraintViolationException exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();
    }

    @ExceptionHandler(AbstractNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomErrorResponse handleNotFoundError(final AbstractNotFoundException exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.NOT_FOUND_ERROR.getName())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    CustomErrorResponse handleProcessError(final Exception exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.PROCESS_ERROR.getName())
                .build();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomErrorResponse handleEndpointNotFoundError(final NoResourceFoundException exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.API_ERROR.getName())
                .build();
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    CustomErrorResponse handleSQLError(final SQLException exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.DATABASE_ERROR.getName())
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    CustomErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.API_ERROR.getName())
                .build();
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    CustomErrorResponse handleDataAccessException(DataAccessException exception) {

        log.error(exception.getMessage(), exception);

        return CustomErrorResponse.builder()
                .header(CustomErrorResponse.Header.DATABASE_ERROR.getName())
                .build();
    }

}
