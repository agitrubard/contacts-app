package dev.agitrubard.contact.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public final class PersonListResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String company;
    private LocalDateTime createdAt;

}
