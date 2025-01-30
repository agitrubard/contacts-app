package dev.agitrubard.contact.model;

import java.time.LocalDateTime;

public class PersonBuilder extends TestDataBuilder<Person> {

    public PersonBuilder() {
        super(Person.class);
    }

    public PersonBuilder withValidValues() {
        return this
                .withCreatedAtNow();
    }

    private PersonBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
