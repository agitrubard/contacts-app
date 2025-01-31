package dev.agitrubard.contact.exception;

import java.io.Serial;
import java.util.UUID;

public class PersonNotFoundException extends AbstractNotFoundException {

    @Serial
    private static final long serialVersionUID = 6244189087502655685L;

    public PersonNotFoundException(UUID id) {
        super("Person not found with id: " + id);
    }

}
