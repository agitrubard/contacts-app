package dev.agitrubard.contact.model.response;

public class CustomErrorResponseBuilder {

    public static final CustomErrorResponse VALIDATION_ERROR = CustomErrorResponse.builder()
            .header(CustomErrorResponse.Header.VALIDATION_ERROR.getName())
            .build();

    public static final CustomErrorResponse NOT_FOUND_ERROR = CustomErrorResponse.builder()
            .header(CustomErrorResponse.Header.NOT_FOUND_ERROR.getName())
            .build();

}
