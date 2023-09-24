package dolmatov.controllers;

import dolmatov.dao.PersonDAO;
import dolmatov.models.Person;
import dolmatov.utils.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
    private PersonDAO personDAO;
    private PersonValidator personValidator;
    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String getPeople(Model model){
        // Всех
        model.addAttribute("people", personDAO.getPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id,
                            Model model){
        // Получим одного
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String addPerson(@ModelAttribute("person") Person person) {
        // вывод на html
        return "people/createPerson";

    }
    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        // Реализовать, что делать, если он не меняет значение, но оно у него есть
        if(bindingResult.hasErrors()){
            return "people/createPerson";
        }
        personDAO.addPerson(person);
        return "redirect:/people";

    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model){
        // Меняем данные пользователя
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/editPerson";
    }

    @PatchMapping("/{id}")
    public String patchPerson(@PathVariable("id") int id,
                              @ModelAttribute("person") @Valid Person person,
                              BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        // Реализовать, что делать, если он не меняет значение, но оно у него есть
        if(bindingResult.hasErrors()){
            return "people/editPerson";
        }

        personDAO.editPerson(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id,
                               @ModelAttribute("person") Person person){
        // Обращаемся сразу к DAO
        personDAO.deletePerson(id, person);
        return "redirect:/people";
    }
}
