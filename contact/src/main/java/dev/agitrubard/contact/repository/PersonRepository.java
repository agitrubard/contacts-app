package dev.agitrubard.contact.repository;

import dev.agitrubard.contact.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepository extends JpaRepository<PersonEntity, String>, JpaSpecificationExecutor<PersonEntity> {
}
