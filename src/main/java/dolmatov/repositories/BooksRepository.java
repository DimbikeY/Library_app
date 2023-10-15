package dolmatov.repositories;

import dolmatov.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    public Book findById(int id);
    public List<Book> findByTitleStartingWithIgnoreCase(String title);

}
