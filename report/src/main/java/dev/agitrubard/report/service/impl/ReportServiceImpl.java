package dev.agitrubard.report.service.impl;

import dev.agitrubard.report.exception.ReportNotFoundException;
import dev.agitrubard.report.exception.ReportTypeNotImplementedException;
import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.enums.ReportType;
import dev.agitrubard.report.port.ReportReadPort;
import dev.agitrubard.report.port.ReportSavePort;
import dev.agitrubard.report.service.ReportCreator;
import dev.agitrubard.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ReportServiceImpl implements ReportService {

    private final ReportReadPort reportReadPort;
    private final ReportSavePort reportSavePort;
    private final List<ReportCreator> reportCreators;


    @Override
    public List<Report> findAll(Integer page, Integer pageSize) {
        return reportReadPort.findAll(page, pageSize);
    }


    @Override
    public Report findById(UUID id) {
        return reportReadPort.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }


    @Override
    public void create(ReportType type) {

        Report reportToBeSave = Report.pending(type);
        Report report = reportSavePort.save(reportToBeSave);

        String data = reportCreators.stream()
                .filter(creator -> type == creator.getType())
                .findFirst()
                .orElseThrow(() -> new ReportTypeNotImplementedException(type))
                .create();

        report.complete(data);
        reportSavePort.save(report);
    }

}
