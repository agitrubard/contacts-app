package dev.agitrubard.contact.exception;

import java.io.Serial;

public abstract class AbstractNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -826751819459043306L;

    protected AbstractNotFoundException(final String message) {
        super(message);
    }

}
