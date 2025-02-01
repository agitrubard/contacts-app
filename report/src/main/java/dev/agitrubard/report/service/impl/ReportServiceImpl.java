package dev.agitrubard.report.service.impl;

import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.port.ReportReadPort;
import dev.agitrubard.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ReportServiceImpl implements ReportService {

    private final ReportReadPort reportReadPort;

    @Override
    public List<Report> findAll(Integer page, Integer pageSize) {
        return reportReadPort.findAll(page, pageSize);
    }

}
