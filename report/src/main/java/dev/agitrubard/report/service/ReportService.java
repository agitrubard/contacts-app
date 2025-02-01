package dev.agitrubard.report.service;

import dev.agitrubard.report.model.Report;

import java.util.List;

public interface ReportService {

    List<Report> findAll(Integer page, Integer size);

}
