package dolmatov.services;

import dolmatov.models.Book;
import dolmatov.models.Person;
import dolmatov.repositories.BooksRepository;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository){
        this.booksRepository = booksRepository;
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
        bookToAttach.setPersonId(book.getPersonId());

        bookToAttach.getPersonId().getBookList().add(bookToAttach);

        booksRepository.save(book);
    }
    @Transactional
    public void releasePersonFromBook(int bookId) {
        Book book = booksRepository.findById(bookId);
        book.setPersonId(null);
        booksRepository.save(book);
    }
    @Transactional
    public Optional<Person> checkBeforeDelete(int id) {
        Book bookToCheck = booksRepository.findById(id);

        return Optional.ofNullable(bookToCheck.getPersonId());
    }
}
