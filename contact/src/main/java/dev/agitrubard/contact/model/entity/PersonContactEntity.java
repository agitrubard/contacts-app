package dev.agitrubard.contact.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "ca_person_contact")
public class PersonContactEntity extends AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;


    @ManyToOne
    private PersonEntity person;

}
