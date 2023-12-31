package dolmatov.controllers;

import dolmatov.models.Book;
import dolmatov.services.BooksService;
import dolmatov.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String getBooks(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                            @RequestParam(value = "books_per_page", required = false, defaultValue = "0") int booksPerPage,
                            @RequestParam(value = "sort_by_year", required = false, defaultValue = "false") boolean isSorted,
                            Model model){
        model.addAttribute("books", booksService.findAll(page, booksPerPage, isSorted));


        return "books/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id,
                          Model model){
        model.addAttribute("book", booksService.findById(id));
        model.addAttribute("people", peopleService.findAll());

        return "books/book";
    }
    @GetMapping("/search")
    public String doSearch(Model model){
        model.addAttribute("book", new Book());

        return "books/search";
    }

    @PostMapping("/search")
    public String doResult(@RequestParam("query") String query,
                           Model model){
        List<Book> booksToCheck = booksService.findByTitleStartingWith(query);
        model.addAttribute("books", booksToCheck);

        return "books/search";
    }

    @GetMapping("/new")
    public String addBook(@ModelAttribute("book") Book book){

        return "books/createBook";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/createBook";
        }
        booksService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id,
                           Model model){
        model.addAttribute("book", booksService.findById(id));

        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String patchBook(@PathVariable("id") int id,
                            @ModelAttribute("book") @Valid Book book,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            return "books/editBook";
        }
        booksService.update(book, id);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id,
                             @ModelAttribute("book") Book book){
        if(booksService.checkBeforeDelete(id).isPresent()){

            return "redirect:/books/" + id ;
        }
        booksService.delete(book, id);

        return "redirect:/books";
    }

    @PatchMapping("/attach/{id}")
    public String attachPersonToBook(@PathVariable("id") int id,
                                     @ModelAttribute("book") Book book){
        booksService.attachPersonToBook(book, id);

        return "redirect:/books/" + id;
    }

    @PatchMapping("/release/{id}")
    public String releasePersonFromBook(@PathVariable("id") int id){
        booksService.releasePersonFromBook(id);

        return "redirect:/books/" + id;
    }
}
