package dev.agitrubard.contact.service.impl;

import dev.agitrubard.contact.AbstractUnitTest;
import dev.agitrubard.contact.exception.PersonNotFoundException;
import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonBuilder;
import dev.agitrubard.contact.model.request.PersonContactAddRequest;
import dev.agitrubard.contact.model.request.PersonContactAddRequestBuilder;
import dev.agitrubard.contact.port.PersonReadPort;
import dev.agitrubard.contact.port.PersonSavePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

class PersonContactServiceImplTest extends AbstractUnitTest {

    @InjectMocks
    PersonContactServiceImpl personContactService;

    @Mock
    PersonReadPort personReadPort;

    @Mock
    PersonSavePort personSavePort;


    @Test
    void givenValidPersonIdAndContactAddRequest_whenPersonContactAdded_thenDoNothing() {

        // Given
        UUID mockPersonId = UUID.fromString("42abb18a-daae-4e1b-922c-c4cef65720c4");
        PersonContactAddRequest mockAddRequest = new PersonContactAddRequestBuilder()
                .withValidValues()
                .build();

        // When
        Person mockPerson = new PersonBuilder()
                .withValidValues()
                .withId(mockPersonId)
                .withoutContacts()
                .build();
        Mockito.when(personReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(mockPerson));

        Mockito.doNothing()
                .when(personSavePort)
                .save(Mockito.any(Person.class));

        // Then
        personContactService.add(mockPersonId, mockAddRequest);

        // Verify
        Mockito.verify(personReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));

        Mockito.verify(personSavePort, Mockito.times(1))
                .save(Mockito.any(Person.class));
    }

    @Test
    void givenPersonIdAndContactAddRequest_whenPersonDoesNotFound_thenThrowPersonNotFoundException() {

        // Given
        UUID mockPersonId = UUID.fromString("89edd4a2-e4dc-49c1-ab88-a1539239538f");
        PersonContactAddRequest mockAddRequest = new PersonContactAddRequestBuilder()
                .withValidValues()
                .build();

        // When
        Mockito.when(personReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(
                PersonNotFoundException.class,
                () -> personContactService.add(mockPersonId, mockAddRequest)
        );

        // Verify
        Mockito.verify(personReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));

        Mockito.verify(personSavePort, Mockito.never())
                .save(Mockito.any(Person.class));
    }

}
