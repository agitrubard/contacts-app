package dev.agitrubard.contact.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CustomSuccessResponse<T> {

    @Builder.Default
    private final LocalDateTime time = LocalDateTime.now();

    @Builder.Default
    private final Boolean isSuccess = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T content;


    public static CustomSuccessResponse<Void> success() {
        return CustomSuccessResponse.<Void>builder()
                .build();
    }

    public static <T> CustomSuccessResponse<T> success(final T content) {
        return CustomSuccessResponse.<T>builder()
                .content(content)
                .build();
    }

}
