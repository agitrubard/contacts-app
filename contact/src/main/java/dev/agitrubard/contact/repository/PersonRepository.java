package dev.agitrubard.contact.repository;

import dev.agitrubard.contact.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID>, JpaSpecificationExecutor<PersonEntity> {
}
