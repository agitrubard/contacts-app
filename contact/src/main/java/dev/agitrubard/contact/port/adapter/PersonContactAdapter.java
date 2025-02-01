package dev.agitrubard.contact.port.adapter;

import dev.agitrubard.contact.model.PersonContact;
import dev.agitrubard.contact.model.mapper.PersonContactEntityToDomainMapper;
import dev.agitrubard.contact.port.PersonContactDeletePort;
import dev.agitrubard.contact.port.PersonContactReadPort;
import dev.agitrubard.contact.repository.PersonContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class PersonContactAdapter implements PersonContactReadPort, PersonContactDeletePort {

    private final PersonContactRepository personContactRepository;

    private final PersonContactEntityToDomainMapper personContactEntityToDomainMapper = PersonContactEntityToDomainMapper.INSTANCE;


    @Override
    public Optional<PersonContact> findById(UUID id) {
        return personContactRepository.findById(id)
                .map(personContactEntityToDomainMapper::map);
    }

    @Override
    public void delete(UUID id) {
        personContactRepository.deleteById(id);
    }

}
