package dev.agitrubard.report.service;

import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.enums.ReportType;

import java.util.List;
import java.util.UUID;

public interface ReportService {

    List<Report> findAll(Integer page, Integer size);

    Report findById(UUID id);

    void create(ReportType type);

}
