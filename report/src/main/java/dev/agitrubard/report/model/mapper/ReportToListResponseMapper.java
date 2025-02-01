package dev.agitrubard.report.model.mapper;

import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.response.ReportListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportToListResponseMapper extends BaseMapper<Report, ReportListResponse> {

    ReportToListResponseMapper INSTANCE = Mappers.getMapper(ReportToListResponseMapper.class);

}
