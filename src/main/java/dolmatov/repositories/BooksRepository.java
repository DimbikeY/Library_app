package dolmatov.repositories;

import dolmatov.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    public Book findById(int id);
    public Book findByTitleStartingWith(String title);

}
