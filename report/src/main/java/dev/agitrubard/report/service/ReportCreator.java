package dev.agitrubard.report.service;

import dev.agitrubard.report.model.enums.ReportType;

public interface ReportCreator {

    ReportType getType();

    void request();

}
