package dev.agitrubard.report.model;

import dev.agitrubard.report.model.enums.ReportStatus;
import dev.agitrubard.report.model.enums.ReportType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Report extends AbstractDomainModel {

    private UUID id;
    private ReportType type;
    @Builder.Default
    private ReportStatus status = ReportStatus.PENDING;
    private String data;

}
