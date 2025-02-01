package dev.agitrubard.contact.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PersonBuilder extends TestDataBuilder<Person> {

    public PersonBuilder() {
        super(Person.class);
    }

    public PersonBuilder withValidValues() {
        return this
                .withCreatedAtNow();
    }

    public PersonBuilder withId(UUID id) {
        data.setId(id);
        return this;
    }

    private PersonBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
