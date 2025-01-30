package dev.agitrubard.contact.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Setter
public final class CustomPageRequest {

    @Range(min = 1)
    @NotNull
    private int page;

    @Getter
    @NotNull
    @Range(min = 1, max = 15)
    private int pageSize;


    public int getPage() {
        return this.page - 1;
    }

}
