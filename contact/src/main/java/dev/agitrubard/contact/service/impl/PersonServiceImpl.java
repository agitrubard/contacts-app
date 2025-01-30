package dev.agitrubard.contact.service.impl;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.port.PersonReadPort;
import dev.agitrubard.contact.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class PersonServiceImpl implements PersonService {

    private final PersonReadPort personReadPort;


    @Override
    public List<Person> findAll(Integer page, Integer size) {
        return personReadPort.findAll(page, size);
    }

}
