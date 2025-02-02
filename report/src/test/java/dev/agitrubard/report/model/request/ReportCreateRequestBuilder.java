package dev.agitrubard.report.model.request;

import dev.agitrubard.report.model.TestDataBuilder;
import dev.agitrubard.report.model.enums.ReportType;

public class ReportCreateRequestBuilder extends TestDataBuilder<ReportCreateRequest> {

    public ReportCreateRequestBuilder() {
        super(ReportCreateRequest.class);
    }

    public ReportCreateRequestBuilder withType(ReportType type) {
        data.setType(type);
        return this;
    }

}
