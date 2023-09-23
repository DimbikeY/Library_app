package dolmatov.controllers;

import dolmatov.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class BookController {

    private PersonDAO personDAO;
    @Autowired
    public BookController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getAll(Model model){
        // Идём через модель в dao -> запрос в SQL -> возрат и отправка в представление через атрибут модели
        model.addAttribute("people", personDAO.getPeople());
        return "person/showAll";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id,
                            Model model){
        // Получим одного через переменную пути -> запрос к БД -> засунем в модель и на представление

    }

    @GetMapping("/new")
    public String addPerson(){
        // вывод на html
    }
    @PostMapping()
    public String createPerson(){

    }

    @GetMapping("/{id}/edit")
    public String editPerson(){
        // Меняем данные пользователя
    }

    @PatchMapping("/{id}")
    public String patchPerson(){
        // обращаюсь к БД после получения html формы от editPerson
    }

    @DeleteMapping("/{id}")
    public String deletePerson(){
        // Обращаемся сразу к DAO
    }
}
