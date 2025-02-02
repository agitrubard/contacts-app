package dev.agitrubard.contact.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PersonStatistic {

    private String city;
    private String district;
    private Long personCount;
    private Long phoneNumberCount;

}
