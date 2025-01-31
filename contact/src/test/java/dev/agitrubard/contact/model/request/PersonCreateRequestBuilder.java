package dev.agitrubard.contact.model.request;

import dev.agitrubard.contact.model.TestDataBuilder;

public class PersonCreateRequestBuilder extends TestDataBuilder<PersonCreateRequest> {

    public PersonCreateRequestBuilder() {
        super(PersonCreateRequest.class);
    }

    public PersonCreateRequestBuilder withFirstName(String firstName) {
        data.setFirstName(firstName);
        return this;
    }

    public PersonCreateRequestBuilder withLastName(String lastName) {
        data.setLastName(lastName);
        return this;
    }

    public PersonCreateRequestBuilder withCompany(String company) {
        data.setCompany(company);
        return this;
    }

}
