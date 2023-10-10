package dolmatov.controllers;

import dolmatov.dao.PersonDAO;
import dolmatov.models.Person;
import dolmatov.services.PeopleService;
import dolmatov.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    private PersonDAO personDAO;
    private PersonValidator personValidator;
    private PeopleService peopleService;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator, PeopleService peopleService) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String getPeople(Model model){
        model.addAttribute("people", peopleService.findAll());

        return "people/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id,
                            Model model){
        model.addAttribute("person", peopleService.findById(id));

        return "people/person";
    }

    @GetMapping("/new")
    public String addPerson(@ModelAttribute("person") Person person) {

        return "people/createPerson";
    }
    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "people/createPerson";
        }
        peopleService.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model){
        model.addAttribute("person", peopleService.findById(id));

        return "people/editPerson";
    }

    @PatchMapping("/{id}")
    public String patchPerson(@PathVariable("id") int id,
                              @ModelAttribute("person") @Valid Person person,
                              BindingResult bindingResult) {
        personValidator.validate(person, bindingResult, id);
        if(bindingResult.hasErrors()){
            return "redirect:/people/editPerson";
        }
        peopleService.update(person, id);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id,
                               @ModelAttribute("person") Person person){
        if(peopleService.checkBeforeDelete(id).isPresent()){
            return "redirect:/people/   " + id;
        }
        peopleService.delete(person, id);

        return "redirect:/people";
    }

}
