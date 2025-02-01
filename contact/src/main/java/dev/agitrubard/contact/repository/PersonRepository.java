package dev.agitrubard.contact.repository;

import dev.agitrubard.contact.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {

    Optional<PersonEntity> findByContactsId(UUID contactId);

}
