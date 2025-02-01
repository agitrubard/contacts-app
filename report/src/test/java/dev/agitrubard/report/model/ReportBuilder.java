package dev.agitrubard.report.model;

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

    public ReportBuilder withData(String data) {
        this.data.setData(data);
        return this;
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
