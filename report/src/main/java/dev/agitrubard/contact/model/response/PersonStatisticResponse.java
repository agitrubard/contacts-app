package dev.agitrubard.contact.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PersonStatisticResponse {

    private String city;
    private String district;
    private Long personCount;
    private Long phoneNumberCount;

}
