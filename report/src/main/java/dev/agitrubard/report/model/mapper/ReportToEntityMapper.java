package dev.agitrubard.report.model.mapper;

import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.entity.ReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportToEntityMapper extends BaseMapper<Report, ReportEntity> {

    ReportToEntityMapper INSTANCE = Mappers.getMapper(ReportToEntityMapper.class);

}
