package dev.agitrubard.contact.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
public class CustomErrorResponse {

    @Builder.Default
    private final LocalDateTime time = LocalDateTime.now();

    @Builder.Default
    private final Boolean isSuccess = false;

    private String header;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;


    @Getter
    @RequiredArgsConstructor
    public enum Header {

        API_ERROR("API ERROR"),
        NOT_FOUND_ERROR("NOT FOUND ERROR"),
        VALIDATION_ERROR("VALIDATION ERROR"),
        DATABASE_ERROR("DATABASE ERROR"),
        PROCESS_ERROR("PROCESS ERROR");

        private final String name;
    }

}
