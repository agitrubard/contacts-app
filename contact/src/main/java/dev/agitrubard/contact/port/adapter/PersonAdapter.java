package dev.agitrubard.contact.port.adapter;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.entity.PersonEntity;
import dev.agitrubard.contact.model.mapper.PersonEntityToDomainMapper;
import dev.agitrubard.contact.model.mapper.PersonToEntityMapper;
import dev.agitrubard.contact.port.PersonDeletePort;
import dev.agitrubard.contact.port.PersonReadPort;
import dev.agitrubard.contact.port.PersonSavePort;
import dev.agitrubard.contact.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class PersonAdapter implements PersonReadPort, PersonSavePort, PersonDeletePort {

    private final PersonRepository personRepository;

    private final PersonEntityToDomainMapper personEntityToDomainMapper = PersonEntityToDomainMapper.INSTANCE;
    private final PersonToEntityMapper personToEntityMapper = PersonToEntityMapper.INSTANCE;


    @Override
    public List<Person> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        List<PersonEntity> personEntities = personRepository.findAll(pageable).getContent();
        return personEntityToDomainMapper.map(personEntities);
    }


    @Override
    public Optional<Person> findById(UUID id) {
        return personRepository.findById(id)
                .map(personEntityToDomainMapper::map);
    }


    @Override
    public void save(Person person) {
        PersonEntity personEntity = personToEntityMapper.map(person);
        personRepository.save(personEntity);
    }


    @Override
    public void delete(UUID id) {
        personRepository.deleteById(id);
    }

}
