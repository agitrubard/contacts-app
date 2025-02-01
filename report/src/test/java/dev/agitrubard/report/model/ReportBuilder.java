package dev.agitrubard.report.model;

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
        data.setId(id);
        return this;
    }

    public ReportBuilder withoutId() {
        data.setId(null);
        return this;
    }

    public ReportBuilder withType(ReportType type) {
        this.data.setType(type);
        return this;
    }

    public ReportBuilder withData(String data) {
        this.data.setData(data);
        return this;
    }

    public ReportBuilder withCreatedAt(LocalDateTime createdAt) {
        data.setCreatedAt(createdAt);
        return this;
    }

    public ReportBuilder withoutCreatedAt() {
        data.setCreatedAt(null);
        return this;
    }

    public ReportBuilder withUpdatedAt(LocalDateTime updatedAt) {
        data.setUpdatedAt(updatedAt);
        return this;
    }

    public ReportBuilder withoutUpdatedAt() {
        data.setUpdatedAt(null);
        return this;
    }

}
