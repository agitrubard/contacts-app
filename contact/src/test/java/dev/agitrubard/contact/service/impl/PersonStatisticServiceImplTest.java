package dev.agitrubard.contact.service.impl;

import dev.agitrubard.contact.AbstractUnitTest;
import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.PersonStatisticBuilder;
import dev.agitrubard.contact.port.PersonStatisticReadPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

class PersonStatisticServiceImplTest extends AbstractUnitTest {

    @InjectMocks
    PersonStatisticServiceImpl personStatisticService;

    @Mock
    PersonStatisticReadPort personStatisticReadPort;


    @Test
    void whenFoundAllStatisticByLocation_thenReturnPersonStatistics() {

        // When
        List<PersonStatistic> mockPersonStatistics = List.of(
                new PersonStatisticBuilder().build(),
                new PersonStatisticBuilder().build()
        );
        Mockito.when(personStatisticReadPort.findAllStatisticsByLocation())
                .thenReturn(mockPersonStatistics);

        // Then
        List<PersonStatistic> personStatistics = personStatisticService.findAllStatisticsByLocation();

        Assertions.assertEquals(mockPersonStatistics.size(), personStatistics.size());

        // Verify
        Mockito.verify(personStatisticReadPort, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

    @Test
    void whenDidNotFindAllStatisticByLocation_thenReturnEmptyList() {

        // When
        Mockito.when(personStatisticReadPort.findAllStatisticsByLocation())
                .thenReturn(List.of());

        // Then
        List<PersonStatistic> personStatistics = personStatisticService.findAllStatisticsByLocation();

        Assertions.assertEquals(0, personStatistics.size());

        // Verify
        Mockito.verify(personStatisticReadPort, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

}
