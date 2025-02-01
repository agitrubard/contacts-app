package dev.agitrubard.report.model.mapper;

import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.entity.ReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportEntityToDomainMapper extends BaseMapper<ReportEntity, Report> {

    ReportEntityToDomainMapper INSTANCE = Mappers.getMapper(ReportEntityToDomainMapper.class);

}
