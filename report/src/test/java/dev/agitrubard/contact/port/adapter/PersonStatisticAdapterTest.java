package dev.agitrubard.contact.port.adapter;

import dev.agitrubard.AbstractUnitTest;
import dev.agitrubard.contact.client.PersonStatisticFeignClient;
import dev.agitrubard.contact.model.PersonStatistic;
import dev.agitrubard.contact.model.response.PersonStatisticResponse;
import dev.agitrubard.contact.model.response.PersonStatisticResponseBuilder;
import dev.agitrubard.report.model.response.CustomSuccessResponse;
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
    PersonStatisticFeignClient personStatisticFeignClient;


    @Test
    void whenFoundAllStatisticByLocation_thenReturnPersonStatistics() {

        // When
        List<PersonStatisticResponse> mockPersonStatisticResponses = List.of(
                new PersonStatisticResponseBuilder().build(),
                new PersonStatisticResponseBuilder().build()
        );
        CustomSuccessResponse<List<PersonStatisticResponse>> mockResponse = CustomSuccessResponse
                .success(mockPersonStatisticResponses);
        Mockito.when(personStatisticFeignClient.findAllStatisticsByLocation())
                .thenReturn(mockResponse);

        // Then
        List<PersonStatistic> personStatistics = personStatisticAdapter.findAllStatisticsByLocation();

        Assertions.assertEquals(mockPersonStatisticResponses.size(), personStatistics.size());

        // Verify
        Mockito.verify(personStatisticFeignClient, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

    @Test
    void whenDidNotFindAllStatisticByLocation_thenReturnEmptyList() {

        // When
        List<PersonStatisticResponse> mockPersonStatisticResponses = List.of();
        CustomSuccessResponse<List<PersonStatisticResponse>> mockResponse = CustomSuccessResponse
                .success(mockPersonStatisticResponses);
        Mockito.when(personStatisticFeignClient.findAllStatisticsByLocation())
                .thenReturn(mockResponse);

        // Then
        List<PersonStatistic> personStatistics = personStatisticAdapter.findAllStatisticsByLocation();

        Assertions.assertEquals(0, personStatistics.size());

        // Verify
        Mockito.verify(personStatisticFeignClient, Mockito.times(1))
                .findAllStatisticsByLocation();
    }

}
