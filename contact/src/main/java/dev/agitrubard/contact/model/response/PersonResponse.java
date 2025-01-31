package dev.agitrubard.contact.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public final class PersonResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String company;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    List<Contact> contacts;

    @Getter
    @Setter
    public static class Contact {
        private UUID id;
        private String emailAddress;
        private String phoneNumber;
        private String city;
        private String district;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}
