package dev.agitrubard.contact.port.adapter;

import dev.agitrubard.contact.AbstractUnitTest;
import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.entity.PersonEntity;
import dev.agitrubard.contact.model.entity.PersonEntityBuilder;
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

class PersonAdapterTest extends AbstractUnitTest {

    @InjectMocks
    PersonAdapter personAdapter;

    @Mock
    PersonRepository personRepository;


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

}
