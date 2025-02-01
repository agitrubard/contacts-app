package dev.agitrubard.report.exception;

import java.io.Serial;
import java.util.UUID;

public final class ReportNotFoundException extends AbstractNotFoundException {

    @Serial
    private static final long serialVersionUID = -8459222745943354210L;

    public ReportNotFoundException(UUID id) {
        super("Report not found with id: " + id);
    }

}
