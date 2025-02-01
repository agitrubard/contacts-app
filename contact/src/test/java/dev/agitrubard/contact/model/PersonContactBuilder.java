package dev.agitrubard.contact.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PersonContactBuilder extends TestDataBuilder<PersonContact> {

    public PersonContactBuilder() {
        super(PersonContact.class);
    }


    public PersonContactBuilder withValidValues() {
        return this
                .withCreatedAtNow();
    }

    public PersonContactBuilder withId(UUID id) {
        data.setId(id);
        return this;
    }

    public PersonContactBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
