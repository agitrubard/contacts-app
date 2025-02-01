package dev.agitrubard.contact.port.adapter;

import dev.agitrubard.contact.AbstractUnitTest;
import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.PersonBuilder;
import dev.agitrubard.contact.model.entity.PersonEntity;
import dev.agitrubard.contact.model.entity.PersonEntityBuilder;
import dev.agitrubard.contact.model.mapper.PersonToEntityMapper;
import dev.agitrubard.contact.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class PersonAdapterTest extends AbstractUnitTest {

    @InjectMocks
    PersonAdapter personAdapter;

    @Mock
    PersonRepository personRepository;


    private final PersonToEntityMapper personToEntityMapper = PersonToEntityMapper.INSTANCE;


    @Test
    void givenValidPageAndPageSize_whenPeopleFound_thenReturnPeople() {

        // Given
        Integer mockPage = 1;
        Integer mockPageSize = 10;

        // When
        Pageable mockPageable = PageRequest.of(mockPage - 1, mockPageSize);

        List<PersonEntity> mockPersonEntities = List.of(
                new PersonEntityBuilder().withValidValues().build(),
                new PersonEntityBuilder().withValidValues().build()
        );
        Page<PersonEntity> mockPersonEntitiesPage = new PageImpl<>(mockPersonEntities);
        Mockito.when(personRepository.findAll(mockPageable))
                .thenReturn(mockPersonEntitiesPage);

        // Then
        List<Person> people = personAdapter.findAll(mockPage, mockPageSize);

        Assertions.assertEquals(mockPersonEntities.size(), people.size());

        // Verify
        Mockito.verify(personRepository, Mockito.times(1))
                .findAll(mockPageable);
    }

    @Test
    void givenValidPageAndPageSize_whenPeopleNotFound_thenReturnEmptyPeople() {

        // Given
        Integer mockPage = 1;
        Integer mockPageSize = 10;

        // When
        Pageable mockPageable = PageRequest.of(mockPage - 1, mockPageSize);
        Mockito.when(personRepository.findAll(mockPageable))
                .thenReturn(Page.empty());

        // Then
        List<Person> people = personAdapter.findAll(mockPage, mockPageSize);

        Assertions.assertEquals(0, people.size());

        // Verify
        Mockito.verify(personRepository, Mockito.times(1))
                .findAll(mockPageable);
    }


    @Test
    void givenValidId_whenPersonFound_thenReturnPerson() {

        // Given
        UUID mockId = UUID.fromString("2d2ca373-911c-4fec-9912-44dd171ba1c5");

        // When
        PersonEntity mockPersonEntity = new PersonEntityBuilder()
                .withValidValues()
                .build();
        Mockito.when(personRepository.findById(mockId))
                .thenReturn(Optional.of(mockPersonEntity));

        // Then
        Optional<Person> person = personAdapter.findById(mockId);

        Assertions.assertTrue(person.isPresent());

        // Verify
        Mockito.verify(personRepository, Mockito.times(1))
                .findById(mockId);
    }

    @Test
    void givenValidId_whenPersonNotFound_thenReturnEmpty() {

        // Given
        UUID mockId = UUID.fromString("b802ee45-6768-481d-b252-ed80bd829c7f");

        // When
        Mockito.when(personRepository.findById(mockId))
                .thenReturn(Optional.empty());

        // Then
        Optional<Person> person = personAdapter.findById(mockId);

        Assertions.assertTrue(person.isEmpty());

        // Verify
        Mockito.verify(personRepository, Mockito.times(1))
                .findById(mockId);
    }


    @Test
    void givenValidPerson_whenPersonSaved_thenDoNothing() {

        // Given
        Person mockPerson = new PersonBuilder()
                .withValidValues()
                .build();

        // When
        PersonEntity mockPersonEntity = personToEntityMapper.map(mockPerson);

        Mockito.when(personRepository.save(Mockito.any(PersonEntity.class)))
                .thenReturn(mockPersonEntity);

        // Then
        personAdapter.save(mockPerson);

        // Verify
        Mockito.verify(personRepository, Mockito.times(1))
                .save(Mockito.any(PersonEntity.class));
    }


    @Test
    void givenValidId_whenPersonDeleted_thenDoNothing() {

        // Given
        UUID mockId = UUID.fromString("02684808-8fa6-4d0f-bdb0-8d8f1a065123");

        // When
        Mockito.doNothing()
                .when(personRepository)
                .deleteById(mockId);

        // Then
        personAdapter.delete(mockId);

        // Verify
        Mockito.verify(personRepository, Mockito.times(1))
                .deleteById(mockId);
    }

}
