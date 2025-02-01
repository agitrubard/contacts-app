package dev.agitrubard.contact.service.impl;

import dev.agitrubard.contact.exception.PersonContactNotFoundException;
import dev.agitrubard.contact.exception.PersonNotFoundException;
import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonContact;
import dev.agitrubard.contact.model.mapper.PersonContactAddRequestToDomainMapper;
import dev.agitrubard.contact.model.request.PersonContactAddRequest;
import dev.agitrubard.contact.port.PersonContactDeletePort;
import dev.agitrubard.contact.port.PersonContactReadPort;
import dev.agitrubard.contact.port.PersonReadPort;
import dev.agitrubard.contact.port.PersonSavePort;
import dev.agitrubard.contact.service.PersonContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class PersonContactServiceImpl implements PersonContactService {

    private final PersonReadPort personReadPort;
    private final PersonSavePort personSavePort;
    private final PersonContactReadPort personContactReadPort;
    private final PersonContactDeletePort personContactDeletePort;

    private final PersonContactAddRequestToDomainMapper personContactAddRequestToDomainMapper = PersonContactAddRequestToDomainMapper.INSTANCE;


    @Override
    public void add(UUID personId, PersonContactAddRequest addRequest) {

        Person person = personReadPort.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));

        PersonContact personContact = personContactAddRequestToDomainMapper.map(addRequest);
        person.getContacts().add(personContact);

        personSavePort.save(person);
    }

    @Override
    public void delete(UUID id) {

        personContactReadPort.findById(id)
                .orElseThrow(() -> new PersonContactNotFoundException(id));

        personContactDeletePort.delete(id);
    }

}
