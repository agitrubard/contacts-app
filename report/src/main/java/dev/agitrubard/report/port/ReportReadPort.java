package dev.agitrubard.report.port;

import dev.agitrubard.report.model.Report;

import java.util.List;

public interface ReportReadPort {

    List<Report> findAll(Integer page, Integer size);

}
