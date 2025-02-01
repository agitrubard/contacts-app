package dev.agitrubard.contact.controller;

import dev.agitrubard.contact.model.request.PersonContactAddRequest;
import dev.agitrubard.contact.model.response.CustomSuccessResponse;
import dev.agitrubard.contact.service.PersonContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
class PersonContactController {

    private final PersonContactService personContactService;


    @PostMapping("/{id}/contact")
    CustomSuccessResponse<Void> add(@PathVariable(name = "id") UUID personId,
                                    @RequestBody @Valid PersonContactAddRequest addRequest) {

        personContactService.add(personId, addRequest);

        return CustomSuccessResponse.success();
    }

}
