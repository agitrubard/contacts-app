package dev.agitrubard.contact.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Person extends AbstractDomainModel {

    private UUID id;
    private String firstName;
    private String lastName;
    private String company;

    @Builder.Default
    private List<PersonContact> contacts = new ArrayList<>();

}
