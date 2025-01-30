package dev.agitrubard.contact.controller;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.mapper.PersonToListResponseMapper;
import dev.agitrubard.contact.model.request.CustomPageRequest;
import dev.agitrubard.contact.model.response.CustomSuccessResponse;
import dev.agitrubard.contact.model.response.PersonListResponse;
import dev.agitrubard.contact.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
class PersonController {

    private final PersonService personService;

    private final PersonToListResponseMapper personToListResponseMapper = PersonToListResponseMapper.INSTANCE;


    @GetMapping
    CustomSuccessResponse<List<PersonListResponse>> findAll(@Valid CustomPageRequest pageRequest) {

        List<Person> people = personService.findAll(
                pageRequest.getPage(),
                pageRequest.getPageSize()
        );

        return CustomSuccessResponse.success(
                personToListResponseMapper.map(people)
        );
    }

}
