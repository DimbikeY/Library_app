package dolmatov.repositories;

import dolmatov.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    public Optional<Person> findByName(String name);
    public Person findById(int id);
}
