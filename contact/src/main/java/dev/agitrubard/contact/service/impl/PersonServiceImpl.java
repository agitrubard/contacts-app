package dev.agitrubard.contact.service.impl;

import dev.agitrubard.contact.exception.PersonNotFoundException;
import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.port.PersonReadPort;
import dev.agitrubard.contact.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class PersonServiceImpl implements PersonService {

    private final PersonReadPort personReadPort;


    @Override
    public List<Person> findAll(Integer page, Integer pageSize) {
        return personReadPort.findAll(page, pageSize);
    }

    @Override
    public Person findById(UUID id) {
        return personReadPort.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

}
