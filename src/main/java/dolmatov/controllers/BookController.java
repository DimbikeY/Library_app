package dolmatov.controllers;

import dolmatov.dao.BookDAO;
import dolmatov.dao.PersonDAO;
import dolmatov.models.Book;
import dolmatov.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getBooks(Model model){
        // Идём через модель в dao -> запрос в SQL -> возрат и отправка в представление через атрибут модели
        model.addAttribute("books", bookDAO.getBooks());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id,
                          Model model){
        // Получим одного через переменную пути -> запрос к БД -> засунем в модель и на представление
        model.addAttribute("book", bookDAO.getBook(id));
        model.addAttribute("people", personDAO.getPeople());
        return "books/book";
    }

    @GetMapping("/new")
    public String addBook(@ModelAttribute("book") Book book){
        // вывод на html
        return "books/createBook";
    }
    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/createBook";
        }
        bookDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id,
                           Model model){
        // Меняем данные пользователя
        model.addAttribute("book", bookDAO.getBook(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String patchBook(@PathVariable("id") int id,
                            @ModelAttribute("book") @Valid Book book,
                            BindingResult bindingResult){
        System.out.println(book.getId());
        System.out.println(id);
        // обращаюсь к БД после получения html формы от editPerson
        if(bindingResult.hasErrors()){
            return "books/editBook";
        }
        bookDAO.editBook(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id,
                             @ModelAttribute("book") Book book){
        // Обращаемся сразу к DAO
        bookDAO.deleteBook(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/attach/{id}")
    public String attachPersonToBook(@PathVariable("id") int bookId,
                                     @ModelAttribute("book") Book book,
                                     Model model){
        bookDAO.attachPersonToBook(book, bookId);
        model.addAttribute("book", bookDAO.getBook(book.getId()));
        model.addAttribute("people", personDAO.getPeople());
        return "books/book";
    }

    @PatchMapping("/release/{id}")
    public String releasePersonFromBook(@PathVariable("id") int personId,
                                        @ModelAttribute("book") Book book,
                                        Model model){
        bookDAO.releasePersonFromBook(book, personId);
        model.addAttribute("book", bookDAO.getBook(book.getId()));
        model.addAttribute("people", personDAO.getPeople());
        return "books/book";
    }
}
