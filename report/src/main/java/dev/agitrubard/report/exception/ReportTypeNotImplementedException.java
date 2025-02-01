package dev.agitrubard.report.exception;

import dev.agitrubard.report.model.enums.ReportType;

import java.io.Serial;

public final class ReportTypeNotImplementedException extends AbstractNotFoundException {

    @Serial
    private static final long serialVersionUID = -448163062936664064L;

    public ReportTypeNotImplementedException(ReportType type) {
        super("Report type is not implemented: " + type);
    }

}
