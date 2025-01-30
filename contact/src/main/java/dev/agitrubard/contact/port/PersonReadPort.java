package dev.agitrubard.contact.port;

import dev.agitrubard.contact.model.Person;

import java.util.List;

public interface PersonReadPort {

    List<Person> findAll(int page, int size);

}
