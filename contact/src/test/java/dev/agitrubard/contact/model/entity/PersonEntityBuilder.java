package dev.agitrubard.contact.model.entity;

import dev.agitrubard.contact.model.TestDataBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class PersonEntityBuilder extends TestDataBuilder<PersonEntity> {

    public PersonEntityBuilder() {
        super(PersonEntity.class);
    }

    public PersonEntityBuilder withValidValues() {
        return this
                .withCreatedAtNow();
    }

    public PersonEntityBuilder withId(UUID id) {
        data.setId(id);
        return this;
    }

    private PersonEntityBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
