package dev.agitrubard.contact.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public final class CustomPageRequest {

    @Range(min = 1)
    @NotNull
    private Integer page;

    @NotNull
    @Range(min = 1, max = 15)
    private Integer pageSize;

}
