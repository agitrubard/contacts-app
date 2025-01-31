package dev.agitrubard.contact.controller;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.mapper.PersonToListResponseMapper;
import dev.agitrubard.contact.model.mapper.PersonToResponseMapper;
import dev.agitrubard.contact.model.request.CustomPageRequest;
import dev.agitrubard.contact.model.request.PersonCreateRequest;
import dev.agitrubard.contact.model.response.CustomSuccessResponse;
import dev.agitrubard.contact.model.response.PersonListResponse;
import dev.agitrubard.contact.model.response.PersonResponse;
import dev.agitrubard.contact.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
class PersonController {

    private final PersonService personService;

    private final PersonToListResponseMapper personToListResponseMapper = PersonToListResponseMapper.INSTANCE;
    private final PersonToResponseMapper personToResponseMapper = PersonToResponseMapper.INSTANCE;


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


    @GetMapping("/{id}")
    CustomSuccessResponse<PersonResponse> findById(@PathVariable UUID id) {

        Person person = personService.findById(id);

        return CustomSuccessResponse.success(
                personToResponseMapper.map(person)
        );
    }


    @PostMapping
    CustomSuccessResponse<Void> create(@RequestBody @Valid PersonCreateRequest createRequest) {

        personService.create(createRequest);

        return CustomSuccessResponse.success();
    }

}
