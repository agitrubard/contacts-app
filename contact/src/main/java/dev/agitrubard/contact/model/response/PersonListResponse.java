package dev.agitrubard.contact.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public final class PersonListResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String company;
    private LocalDateTime createdAt;

}
