package dolmatov.repositories;

import dolmatov.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    public Book findById(int id);
    public List<Book> findByTitleStartingWithIgnoreCase(String title);

}
