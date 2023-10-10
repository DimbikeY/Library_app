package dolmatov.services;

import dolmatov.models.Book;
import dolmatov.models.Person;
import dolmatov.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }

    @Transactional(readOnly = true)
    public List<Person> findAll() {

       return peopleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person findById(int id){
        Person person = peopleRepository.findById(id);
        Hibernate.initialize(person.getBookList());

        return person;
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person person, int id) {
        Person personToUpdate = peopleRepository.findById(id);
        personToUpdate.setName(person.getName());
        personToUpdate.setYear(person.getYear());
        peopleRepository.save(personToUpdate);
    }
    @Transactional
    public void delete(Person person, int id) {
        Person personToDelete = peopleRepository.findById(id);
        peopleRepository.delete(personToDelete);
    }

    @Transactional
    public Optional<Person> makeValidationNameUnique(String name) {

        return peopleRepository.findByName(name);
    }

    @Transactional
    public Optional<Book> checkBeforeDelete(int id) {

        return peopleRepository.findById(id).getBookList().stream().findAny();
    }

    @Transactional
    public boolean  checkEditUniqueValidation(Person person, int id) {
        Person personToUniqueEditCheck = peopleRepository.findById(id);

        return Objects.equals(personToUniqueEditCheck.getName(), person.getName());
    }
}
