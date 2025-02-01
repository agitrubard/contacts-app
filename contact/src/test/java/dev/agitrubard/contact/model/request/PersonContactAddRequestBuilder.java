package dev.agitrubard.contact.model.request;

import dev.agitrubard.contact.model.TestDataBuilder;

public class PersonContactAddRequestBuilder extends TestDataBuilder<PersonContactAddRequest> {

    public PersonContactAddRequestBuilder() {
        super(PersonContactAddRequest.class);
    }

    public PersonContactAddRequestBuilder withValidValues() {
        return this
                .withEmailAddress("john.doe@contacts.test")
                .withPhoneNumber("905051234567")
                .withCity("İstanbul")
                .withDistrict("Kadıköy");
    }

    public PersonContactAddRequestBuilder withEmailAddress(String emailAddress) {
        data.setEmailAddress(emailAddress);
        return this;
    }

    public PersonContactAddRequestBuilder withPhoneNumber(String phoneNumber) {
        data.setPhoneNumber(phoneNumber);
        return this;
    }

    public PersonContactAddRequestBuilder withCity(String city) {
        data.setCity(city);
        return this;
    }

    public PersonContactAddRequestBuilder withDistrict(String district) {
        data.setDistrict(district);
        return this;
    }

}
