package dev.agitrubard.contact.exception;

import java.io.Serial;
import java.util.UUID;

public final class PersonNotFoundException extends AbstractNotFoundException {

    @Serial
    private static final long serialVersionUID = -1581724245959529343L;

    public PersonNotFoundException(UUID id) {
        super("Person not found with id: " + id);
    }

}
