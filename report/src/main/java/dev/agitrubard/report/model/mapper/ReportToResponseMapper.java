package dev.agitrubard.report.model.mapper;

import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.response.ReportResponse;
import dev.agitrubard.report.util.JsonUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {JsonUtil.class})
public interface ReportToResponseMapper extends BaseMapper<Report, ReportResponse> {

    ReportToResponseMapper INSTANCE = Mappers.getMapper(ReportToResponseMapper.class);

    @Override
    @Mapping(target = "data", source = "data", qualifiedByName = "MapUtil.toMap")
    ReportResponse map(Report source);

}
