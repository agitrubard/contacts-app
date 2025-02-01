package dev.agitrubard.contact.model.entity;

import dev.agitrubard.contact.model.TestDataBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class PersonContactEntityBuilder extends TestDataBuilder<PersonContactEntity> {

    public PersonContactEntityBuilder() {
        super(PersonContactEntity.class);
    }


    public PersonContactEntityBuilder withValidValues() {
        return this
                .withCreatedAtNow();
    }

    public PersonContactEntityBuilder withId(UUID id) {
        data.setId(id);
        return this;
    }

    public PersonContactEntityBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
