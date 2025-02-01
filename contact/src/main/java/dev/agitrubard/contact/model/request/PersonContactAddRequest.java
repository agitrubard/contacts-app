package dev.agitrubard.contact.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PersonContactAddRequest {

    @Email
    @NotBlank
    @Size(min = 6, max = 254)
    private String emailAddress;

    @NotBlank
    @Size(min = 12, max = 12)
    private String phoneNumber;

    @NotBlank
    @Size(min = 2, max = 100)
    private String city;

    @NotBlank
    @Size(min = 2, max = 100)
    private String district;

}
