package dev.agitrubard.contact.model.entity;

import dev.agitrubard.contact.model.TestDataBuilder;

import java.time.LocalDateTime;

public class PersonEntityBuilder extends TestDataBuilder<PersonEntity> {

    public PersonEntityBuilder() {
        super(PersonEntity.class);
    }

    public PersonEntityBuilder withValidValues() {
        return this
                .withCreatedAtNow();
    }

    private PersonEntityBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
