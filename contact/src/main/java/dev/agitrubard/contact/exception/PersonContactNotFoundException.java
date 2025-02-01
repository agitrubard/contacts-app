package dev.agitrubard.contact.exception;

import java.io.Serial;
import java.util.UUID;

public final class PersonContactNotFoundException extends AbstractNotFoundException {

    @Serial
    private static final long serialVersionUID = 4969686449357124687L;

    public PersonContactNotFoundException(UUID id) {
        super("Person contact not found with id: " + id);
    }

}
