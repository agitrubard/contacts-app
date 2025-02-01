package dev.agitrubard.report.model.entity;

import dev.agitrubard.report.model.TestDataBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReportEntityBuilder extends TestDataBuilder<ReportEntity> {

    public ReportEntityBuilder() {
        super(ReportEntity.class);
    }


    public ReportEntityBuilder withValidValues() {
        return this
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now().plusMinutes(1));
    }

    public ReportEntityBuilder withId(UUID id) {
        data.setId(id);
        return this;
    }

    public ReportEntityBuilder withCreatedAt(LocalDateTime createdAt) {
        data.setCreatedAt(createdAt);
        return this;
    }

    public ReportEntityBuilder withUpdatedAt(LocalDateTime updatedAt) {
        data.setUpdatedAt(updatedAt);
        return this;
    }

}
