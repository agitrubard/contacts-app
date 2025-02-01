package dev.agitrubard.report.port;

import dev.agitrubard.report.model.Report;

public interface ReportSavePort {

    Report save(Report report);

}
