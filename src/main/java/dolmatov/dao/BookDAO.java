package dolmatov.dao;

import dolmatov.models.Book;
import dolmatov.models.BookMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapping());
    }

    public Book getBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BookMapping(), id).stream().findAny().orElse(null);
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)",
                book.getName(),
                book.getAuthor(),
                book.getYear());
    }

    public void editBook(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=?, personId=? WHERE id=?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                book.getPersonId(),
                id);
    }

    public void deleteBook(int id, Book bookToDelete) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",
                bookToDelete.getId());
    }

    public void changePerson(int personId, int id) {
        jdbcTemplate.update("UPDATE Books SET person_id=? WHERE id=?", personId, id);

    }
}
