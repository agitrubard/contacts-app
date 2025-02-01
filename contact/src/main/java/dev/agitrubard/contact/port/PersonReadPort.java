package dev.agitrubard.contact.port;

import dev.agitrubard.contact.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonReadPort {

    List<Person> findAll(Integer page, Integer size);

    Optional<Person> findById(UUID id);

    Optional<Person> findByContactId(UUID id);

}
