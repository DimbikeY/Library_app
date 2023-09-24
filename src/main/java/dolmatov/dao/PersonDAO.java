package dolmatov.dao;

import dolmatov.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Person> makeValidationNameUnique(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new BeanPropertyRowMapper<>(Person.class),
                name).stream().findAny();
    }

    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, year) VALUES (?, ?)", person.getName(), person.getYear());
    }

    public List<Person> getPeople() {
        jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id);
    }

    public void editPerson(Person person, int id) {
        jdbcTemplate.update("UPDATE Person SET name=?, year=? WHERE id=?",
                person.getName(), person.getYear(), id);
    }

    public void deletePerson(int id, Person personToDelete) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",
                personToDelete.getId());
    }
}
