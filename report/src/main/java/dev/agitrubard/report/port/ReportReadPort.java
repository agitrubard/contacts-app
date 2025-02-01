package dev.agitrubard.report.port;

import dev.agitrubard.report.model.Report;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportReadPort {

    List<Report> findAll(Integer page, Integer size);

    Optional<Report> findById(UUID id);

}
