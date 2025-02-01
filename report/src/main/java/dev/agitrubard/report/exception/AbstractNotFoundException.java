package dev.agitrubard.report.exception;

import java.io.Serial;

public abstract class AbstractNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4566431014798020305L;

    protected AbstractNotFoundException(final String message) {
        super(message);
    }

}
