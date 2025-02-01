package dev.agitrubard.report.model.response;

import dev.agitrubard.report.model.enums.ReportStatus;
import dev.agitrubard.report.model.enums.ReportType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public final class ReportResponse {

    private UUID id;
    private ReportType type;
    private ReportStatus status;
    private Map<Object, Object> data;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
