package dev.agitrubard.contact.service.impl;

import dev.agitrubard.contact.AbstractUnitTest;
import dev.agitrubard.contact.exception.PersonNotFoundException;
import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonBuilder;
import dev.agitrubard.contact.model.request.PersonCreateRequest;
import dev.agitrubard.contact.model.request.PersonCreateRequestBuilder;
import dev.agitrubard.contact.port.PersonReadPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


    @Test
    void givenValidId_whenPersonFound_thenReturnPerson() {

        // Given
        UUID mockId = UUID.fromString("423de977-c41d-4095-a4ec-865fc49fd5c5");

        // When
        Person mockPerson = new PersonBuilder()
                .withValidValues()
                .build();
        Mockito.when(personReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(mockPerson));

        // Then
        Person person = personService.findById(mockId);

        Assertions.assertEquals(mockPerson, person);

        // Verify
        Mockito.verify(personReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
    }

    @Test
    void givenValidId_whenPersonNotFound_thenThrowPersonNotFoundException() {

        // Given
        UUID mockId = UUID.fromString("8fd17110-9cac-4ed8-9575-ed9e34538d85");

        // When
        Mockito.when(personReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(
                PersonNotFoundException.class,
                () -> personService.findById(mockId)
        );

        // Verify
        Mockito.verify(personReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
    }


    @Test
    void givenValidPersonCreateRequest_whenPersonCreated_thenDoNothing() {

        // Given
        PersonCreateRequest mockCreateRequest = new PersonCreateRequestBuilder()
                .build();

        // When
        Mockito.doNothing()
                .when(personReadPort)
                .save(Mockito.any(Person.class));

        // Then
        personService.create(mockCreateRequest);

        // Verify
        Mockito.verify(personReadPort, Mockito.times(1))
                .save(Mockito.any(Person.class));
    }


    @Test
    void givenValidId_whenPersonFound_thenDeletePerson() {

        // Given
        UUID mockId = UUID.fromString("9856ca0f-fde3-4b87-9184-17a6ff55a9d0");

        // When
        Person mockPerson = new PersonBuilder()
                .withValidValues()
                .build();
        Mockito.when(personReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(mockPerson));

        Mockito.doNothing()
                .when(personReadPort)
                .delete(Mockito.any(UUID.class));

        // Then
        personService.delete(mockId);

        // Verify
        Mockito.verify(personReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));

        Mockito.verify(personReadPort, Mockito.times(1))
                .delete(Mockito.any(UUID.class));
    }

    @Test
    void givenValidIdForDeleting_whenPersonNotFound_thenThrowPersonNotFoundException() {

        // Given
        UUID mockId = UUID.fromString("fdf209ce-b541-4da7-897b-d525ac23ccfa");

        // When
        Mockito.when(personReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(
                PersonNotFoundException.class,
                () -> personService.findById(mockId)
        );

        // Verify
        Mockito.verify(personReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));

        Mockito.verify(personReadPort, Mockito.never())
                .delete(Mockito.any(UUID.class));
    }

}
