package dev.agitrubard.contact.controller;

import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.mapper.PersonStatisticToResponseMapper;
import dev.agitrubard.contact.model.response.CustomSuccessResponse;
import dev.agitrubard.contact.model.response.PersonStatisticResponse;
import dev.agitrubard.contact.service.PersonStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/person/statistic")
@RequiredArgsConstructor
class PersonStatisticController {

    private final PersonStatisticService personStatisticService;


    private final PersonStatisticToResponseMapper personStatisticToResponseMapper = PersonStatisticToResponseMapper.INSTANCE;


    @GetMapping
    CustomSuccessResponse<List<PersonStatisticResponse>> findAllStatisticsByLocation() {

        List<PersonStatistic> personStatistics = personStatisticService.findAllStatisticsByLocation();

        return CustomSuccessResponse.success(
                personStatisticToResponseMapper.map(personStatistics)
        );
    }

}
