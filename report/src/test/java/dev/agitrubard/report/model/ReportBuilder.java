package dev.agitrubard.report.model;

import dev.agitrubard.report.model.enums.ReportStatus;
import dev.agitrubard.report.model.enums.ReportType;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReportBuilder extends TestDataBuilder<Report> {

    public ReportBuilder() {
        super(Report.class);
    }

    public ReportBuilder withValidValues() {
        return this
                .withData("{\"key\": \"value\"}")
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now().plusMinutes(1));
    }

    public ReportBuilder withId(UUID id) {
        this.data.setId(id);
        return this;
    }

    public ReportBuilder withoutId() {
        this.data.setId(null);
        return this;
    }

    public ReportBuilder withType(ReportType type) {
        this.data.setType(type);
        return this;
    }

    public ReportBuilder withStatus(ReportStatus status) {
        this.data.setStatus(status);
        return this;
    }

    public ReportBuilder withData(String data) {
        this.data.setData(data);
        return this;
    }

    public ReportBuilder withoutData() {
        this.data.setData(null);
        return this;
    }

    public ReportBuilder withCreatedAt(LocalDateTime createdAt) {
        this.data.setCreatedAt(createdAt);
        return this;
    }

    public ReportBuilder withoutCreatedAt() {
        this.data.setCreatedAt(null);
        return this;
    }

    public ReportBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.data.setUpdatedAt(updatedAt);
        return this;
    }

    public ReportBuilder withoutUpdatedAt() {
        this.data.setUpdatedAt(null);
        return this;
    }

}
