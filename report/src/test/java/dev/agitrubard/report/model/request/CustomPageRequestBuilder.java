package dev.agitrubard.report.model.request;

import dev.agitrubard.report.model.TestDataBuilder;

public class CustomPageRequestBuilder extends TestDataBuilder<CustomPageRequest> {

    public CustomPageRequestBuilder() {
        super(CustomPageRequest.class);
    }

    public CustomPageRequestBuilder withValidValues() {
        return this
                .withPage(1)
                .withPageSize(10);
    }

    public CustomPageRequestBuilder withPage(Integer page) {

        data.setPage(page);
        return this;
    }

    public CustomPageRequestBuilder withPageSize(Integer pageSize) {
        data.setPageSize(pageSize);
        return this;
    }

}
