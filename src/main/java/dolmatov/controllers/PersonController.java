package dolmatov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class PersonController {
    @GetMapping()
    public String getBooks(){
        // Всех
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id){
        // Получим одного
    }

    @GetMapping("/new")
    public String addBook(){
        // вывод на html
    }
    @PostMapping()
    public String createBook(){

    }

    @GetMapping("/{id}/edit")
    public String editBook(){
        // Меняем данные пользователя
    }

    @PatchMapping("/{id}")
    public String patchBook(){
        // обращаюсь к БД после получения html формы от editPerson
    }

    @DeleteMapping("/{id}")
    public String deleteBook(){
        // Обращаемся сразу к DAO
    }
}
