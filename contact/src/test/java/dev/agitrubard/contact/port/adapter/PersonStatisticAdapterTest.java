package dev.agitrubard.contact.port.adapter;

import dev.agitrubard.contact.AbstractUnitTest;
import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.entity.PersonStatisticEntity;
import dev.agitrubard.contact.model.entity.PersonStatisticEntityBuilder;
import dev.agitrubard.contact.repository.PersonStatisticRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

class PersonStatisticAdapterTest extends AbstractUnitTest {

    @InjectMocks
    PersonStatisticAdapter personStatisticAdapter;

    @Mock
    PersonStatisticRepository personStatisticRepository;


    @Test
    void whenFoundAllStatisticByLocation_thenReturnPersonStatistics() {

        // When
        List<PersonStatisticEntity> mockPersonStatisticEntities = List.of(
                new PersonStatisticEntityBuilder().build(),
                new PersonStatisticEntityBuilder().build()
        );
        Mockito.when(personStatisticRepository.findAllStatisticsByLocation())
                .thenReturn(mockPersonStatisticEntities);

        // Then
        List<PersonStatistic> personStatistics = personStatisticAdapter.findAllStatisticsByLocation();

        Assertions.assertEquals(mockPersonStatisticEntities.size(), personStatistics.size());

        // Verify
        Mockito.verify(personStatisticRepository, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

    @Test
    void whenDidNotFindAllStatisticByLocation_thenReturnEmptyList() {

        // When
        Mockito.when(personStatisticRepository.findAllStatisticsByLocation())
                .thenReturn(List.of());

        // Then
        List<PersonStatistic> personStatistics = personStatisticAdapter.findAllStatisticsByLocation();

        Assertions.assertEquals(0, personStatistics.size());

        // Verify
        Mockito.verify(personStatisticRepository, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

}
