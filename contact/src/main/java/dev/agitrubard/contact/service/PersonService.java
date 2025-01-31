package dev.agitrubard.contact.service;

import dev.agitrubard.contact.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    List<Person> findAll(Integer page, Integer size);

    Person findById(UUID id);

}
