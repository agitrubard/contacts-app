package dev.agitrubard.contact.service.impl;

import dev.agitrubard.contact.AbstractUnitTest;
import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonBuilder;
import dev.agitrubard.contact.port.PersonReadPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

class PersonServiceImplTest extends AbstractUnitTest {

    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    PersonReadPort personReadPort;


    @Test
    void givenValidPageAndPageSize_whenPeopleFound_thenReturnPeople() {

        // Given
        Integer mockPage = 1;
        Integer mockPageSize = 10;

        // When
        List<Person> mockPeople = List.of(
                new PersonBuilder().withValidValues().build(),
                new PersonBuilder().withValidValues().build()
        );

        Mockito.when(personReadPort.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(mockPeople);

        // Then
        List<Person> people = personService.findAll(mockPage, mockPageSize);

        Assertions.assertEquals(mockPeople.size(), people.size());

        // Verify
        Mockito.verify(personReadPort, Mockito.times(1))
                .findAll(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void givenValidPageAndPageSize_whenPeopleNotFound_thenReturnEmptyPeople() {

        // Given
        Integer mockPage = 1;
        Integer mockPageSize = 10;

        // When
        Mockito.when(personReadPort.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(List.of());

        // Then
        List<Person> people = personService.findAll(mockPage, mockPageSize);

        Assertions.assertEquals(0, people.size());

        // Verify
        Mockito.verify(personReadPort, Mockito.times(1))
                .findAll(Mockito.anyInt(), Mockito.anyInt());
    }

}
