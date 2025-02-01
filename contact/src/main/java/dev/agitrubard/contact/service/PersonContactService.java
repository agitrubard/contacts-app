package dev.agitrubard.contact.service;

import dev.agitrubard.contact.model.request.PersonContactAddRequest;

import java.util.UUID;

public interface PersonContactService {

    void add(UUID personId, PersonContactAddRequest addRequest);

    void delete(UUID id);

}
