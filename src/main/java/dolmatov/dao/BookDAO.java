package dolmatov.dao;

import dolmatov.models.Book;
import dolmatov.models.BookMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                id);
    }

    public void deleteBook(int id, Book bookToDelete) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",
                bookToDelete.getId());
    }
}
