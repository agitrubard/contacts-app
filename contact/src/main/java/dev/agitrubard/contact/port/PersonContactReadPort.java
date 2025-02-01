package dev.agitrubard.contact.port;

import dev.agitrubard.contact.model.PersonContact;

import java.util.Optional;
import java.util.UUID;

public interface PersonContactReadPort {

    Optional<PersonContact> findById(UUID id);

}
