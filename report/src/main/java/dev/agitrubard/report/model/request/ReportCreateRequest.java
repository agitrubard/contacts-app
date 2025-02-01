package dev.agitrubard.report.model.request;

import dev.agitrubard.report.model.enums.ReportType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ReportCreateRequest {

    @NotNull
    private ReportType type;

}
