package dev.agitrubard.contact.exception;

import java.io.Serial;

public abstract class AbstractProcessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2225935273614768465L;

    protected AbstractProcessException(final String message) {
        super(message);
    }

}
