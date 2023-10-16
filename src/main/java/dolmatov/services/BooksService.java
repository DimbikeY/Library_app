package dolmatov.services;

import dolmatov.models.Book;
import dolmatov.models.Person;
import dolmatov.repositories.BooksRepository;

import dolmatov.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<Book> findAll(int page, int booksPerPage, boolean isSorted) {
        List<Book> bookList;
        if(booksPerPage == 0){
            if(isSorted){
                bookList = booksRepository.findAll(Sort.by("year"));
            }else {
                bookList = booksRepository.findAll();
            }
        } else if (isSorted) {
            bookList = booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        }else{
            bookList = booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        }

        return bookList;
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
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setYear(book.getYear());
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

        // a reverse action for local cache
        if(personToAttach.getBookList().isEmpty()){
            personToAttach.setBookList(new ArrayList<>(Collections.singletonList(bookToAttach)));
        }else{
            personToAttach.getBookList().add(bookToAttach);
        }
        //
        bookToAttach.setTimeAt();
    }

    @Transactional
    public void releasePersonFromBook(int bookId) {
        Book bookToRelease = booksRepository.findById(bookId);

        // a reverse action for local cache
        bookToRelease.getPersonId().getBookList().remove(bookToRelease);
        //
        bookToRelease.setPersonId(null);
        if(bookToRelease.isExpired()){
            bookToRelease.setExpired(false);
        }

        bookToRelease.releaseTimeAt();
    }

    @Transactional(readOnly = true)
    public Optional<Person> checkBeforeDelete(int id) {
        Book bookToCheck = booksRepository.findById(id);

        return Optional.ofNullable(bookToCheck.getPersonId());
    }

    @Transactional
    public List<Book> findByTitleStartingWith(String title) {
        List<Book> bookList = booksRepository.findByTitleStartingWithIgnoreCase(title);
        for(Book book: bookList){
            if(book.getPersonId() != null){
                Hibernate.initialize(book.getPersonId().getName());
            }
        }

        return bookList;
    }
}
