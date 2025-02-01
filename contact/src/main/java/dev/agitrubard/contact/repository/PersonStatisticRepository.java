package dev.agitrubard.contact.repository;

import dev.agitrubard.contact.model.entity.PersonStatisticEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersonStatisticRepository extends Repository<PersonStatisticEntity, String> {

    @Query(
            value = """
                    select contact.city                as city,
                           contact.district            as district,
                           count(distinct person.id)   as person_count,
                           count(contact.phone_number) as phone_number_count
                    from ca_person person
                    join ca_person_contact contact on person.id = contact.person_id
                    group by contact.city, contact.district
                    """,
            nativeQuery = true
    )
    List<PersonStatisticEntity> findAllStatisticsByLocation();

}
