package dev.agitrubard.report.model.response;

import dev.agitrubard.report.model.enums.ReportStatus;
import dev.agitrubard.report.model.enums.ReportType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public final class ReportListResponse {

    private UUID id;
    private ReportType type;
    private ReportStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
