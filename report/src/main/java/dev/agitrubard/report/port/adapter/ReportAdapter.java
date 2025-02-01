package dev.agitrubard.report.port.adapter;

import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.entity.ReportEntity;
import dev.agitrubard.report.model.mapper.ReportEntityToDomainMapper;
import dev.agitrubard.report.model.mapper.ReportToEntityMapper;
import dev.agitrubard.report.port.ReportReadPort;
import dev.agitrubard.report.port.ReportSavePort;
import dev.agitrubard.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class ReportAdapter implements ReportReadPort, ReportSavePort {

    private final ReportRepository reportRepository;

    private final ReportEntityToDomainMapper reportEntityToDomainMapper = ReportEntityToDomainMapper.INSTANCE;
    private final ReportToEntityMapper reportToEntityMapper = ReportToEntityMapper.INSTANCE;


    @Override
    public List<Report> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        List<ReportEntity> reportEntities = reportRepository.findAll(pageable).getContent();
        return reportEntityToDomainMapper.map(reportEntities);
    }


    @Override
    public Optional<Report> findById(UUID id) {
        return reportRepository.findById(id)
                .map(reportEntityToDomainMapper::map);
    }


    @Override
    public Report save(Report report) {
        ReportEntity reportEntityToBeSave = reportToEntityMapper.map(report);
        ReportEntity reportEntity = reportRepository.save(reportEntityToBeSave);
        return reportEntityToDomainMapper.map(reportEntity);
    }

}
