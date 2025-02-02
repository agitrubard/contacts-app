package dev.agitrubard.report.model;

import dev.agitrubard.report.model.enums.ReportStatus;
import dev.agitrubard.report.model.enums.ReportType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Report extends AbstractDomainModel {

    private UUID id;
    private ReportType type;
    private ReportStatus status;
    private String data;

    public static Report pending(ReportType type) {
        return Report.builder()
                .type(type)
                .status(ReportStatus.PENDING)
                .build();
    }

    public void processing() {
        this.status = ReportStatus.PROCESSING;
    }

    public void complete(String data) {
        this.data = data;
        this.status = ReportStatus.COMPLETED;
    }

    public void fail() {
        this.status = ReportStatus.FAILED;
    }

}
