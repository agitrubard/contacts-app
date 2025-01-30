package dev.agitrubard.contact.port.adapter;

import dev.agitrubard.contact.model.Person;
import dev.agitrubard.contact.model.entity.PersonEntity;
import dev.agitrubard.contact.model.mapper.PersonEntityToDomainMapper;
import dev.agitrubard.contact.port.PersonReadPort;
import dev.agitrubard.contact.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class PersonAdapter implements PersonReadPort {

    private final PersonRepository personRepository;

    private final PersonEntityToDomainMapper personEntityToDomainMapper = PersonEntityToDomainMapper.INSTANCE;


    @Override
    public List<Person> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        List<PersonEntity> personEntities = personRepository.findAll(pageable).getContent();
        return personEntityToDomainMapper.map(personEntities);
    }

}
