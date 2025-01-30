package dev.agitrubard.contact.service;

import dev.agitrubard.contact.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll(Integer page, Integer size);

}
