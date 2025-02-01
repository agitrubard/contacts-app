package dev.agitrubard.contact.client;

import dev.agitrubard.contact.model.response.PersonStatisticResponse;
import dev.agitrubard.report.model.response.CustomSuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "contact", url = "${feign-client.contact.url}", path = "/api/v1/person/statistic")
public interface PersonStatisticFeignClient {

    @GetMapping
    CustomSuccessResponse<List<PersonStatisticResponse>> findAllStatisticsByLocation();

}
