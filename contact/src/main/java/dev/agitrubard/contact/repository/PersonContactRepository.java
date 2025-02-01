package dev.agitrubard.contact.repository;

import dev.agitrubard.contact.model.entity.PersonContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonContactRepository extends JpaRepository<PersonContactEntity, UUID> {
}
