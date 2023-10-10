package dolmatov.services;

import dolmatov.models.Book;
import dolmatov.models.Person;
import dolmatov.repositories.BooksRepository;

import dolmatov.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository){
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }
    @Transactional(readOnly = true)
    public List<Book> findAll() {

       return booksRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findById(int id){
        Book book = booksRepository.findById(id);
        Hibernate.initialize(book.getPersonId());

        return book;
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book book, int id) {
        Book bookToUpdate = booksRepository.findById(id);
        bookToUpdate.setName(book.getName());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setYear(book.getYear());
        booksRepository.save(bookToUpdate);
    }

    @Transactional
    public void delete(Book book, int id) {
        Book bookToDelete = booksRepository.findById(id);
        booksRepository.delete(bookToDelete);
    }

    @Transactional
    public void attachPersonToBook(Book book, int bookId) {
        Book bookToAttach = booksRepository.findById(bookId);
        Person personToAttach = peopleRepository.findById(book.getPersonId().getId());
        bookToAttach.setPersonId(personToAttach);

        // for local cash
        if(personToAttach.getBookList().isEmpty()){
            personToAttach.setBookList(new ArrayList<>(Collections.singletonList(bookToAttach)));
        }
        //
        booksRepository.save(bookToAttach);
    }

    @Transactional
    public void releasePersonFromBook(int bookId) {
        Book bookToRelease = booksRepository.findById(bookId);
        bookToRelease.setPersonId(null);

        // for local cash
            bookToRelease.getPersonId().getBookList().remove(bookToRelease);
        //

        booksRepository.save(bookToRelease);
    }
    @Transactional
    public Optional<Person> checkBeforeDelete(int id) {
        Book bookToCheck = booksRepository.findById(id);

        return Optional.ofNullable(bookToCheck.getPersonId());
    }
}
