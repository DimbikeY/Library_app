package dolmatov.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapping implements RowMapper<Book> {
    // Для Get запросов
    // Post/patch/delete -> VARARGS
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setYear(rs.getInt("year"));
        book.setAuthor(rs.getString("author"));
        book.setPersonId(rs.getInt("person_id"));
        return book;
    }
}
