package dev.agitrubard.report.controller;

import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.model.mapper.ReportToListResponseMapper;
import dev.agitrubard.report.model.request.CustomPageRequest;
import dev.agitrubard.report.model.response.CustomSuccessResponse;
import dev.agitrubard.report.model.response.ReportListResponse;
import dev.agitrubard.report.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
class ReportController {

    private final ReportService reportService;

    private final ReportToListResponseMapper reportToListResponseMapper = ReportToListResponseMapper.INSTANCE;


    @GetMapping
    CustomSuccessResponse<List<ReportListResponse>> findAll(@Valid CustomPageRequest pageRequest) {

        List<Report> reports = reportService.findAll(
                pageRequest.getPage(),
                pageRequest.getPageSize()
        );

        return CustomSuccessResponse.success(
                reportToListResponseMapper.map(reports)
        );
    }

}
