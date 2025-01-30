package dev.agitrubard.contact.model.response;

public class CustomSuccessResponseBuilder {

    public static <T> CustomSuccessResponse<T> success() {
        return CustomSuccessResponse.<T>builder()
                .build();
    }

}
