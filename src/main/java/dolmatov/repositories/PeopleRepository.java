package dolmatov.repositories;

import dolmatov.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    public Optional<Person> findByName(String name);
    public Person findById(int id);
}
