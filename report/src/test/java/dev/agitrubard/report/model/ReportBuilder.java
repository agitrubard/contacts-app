package dev.agitrubard.report.model;

import java.time.LocalDateTime;

public class ReportBuilder extends TestDataBuilder<Report> {

    public ReportBuilder() {
        super(Report.class);
    }

    public ReportBuilder withValidValues() {
        return this
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now().plusMinutes(1));
    }

    public ReportBuilder withCreatedAt(LocalDateTime createdAt) {
        data.setCreatedAt(createdAt);
        return this;
    }

    public ReportBuilder withUpdatedAt(LocalDateTime updatedAt) {
        data.setUpdatedAt(updatedAt);
        return this;
    }

}
