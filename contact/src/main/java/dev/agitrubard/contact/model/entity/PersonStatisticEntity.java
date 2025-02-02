package dev.agitrubard.contact.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class PersonStatisticEntity {

    @Id
    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "person_count")
    private Long personCount;

    @Column(name = "phone_number_count")
    private Long phoneNumberCount;

}
