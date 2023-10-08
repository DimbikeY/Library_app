package dolmatov.utils;

import dolmatov.models.Person;
import dolmatov.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private PeopleService peopleService;
    @Autowired
    public PersonValidator(PeopleService peopleService){
        this.peopleService = peopleService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(peopleService.makeValidationNameUnique(person.getName()).isPresent()){
            rejectValue(errors);
        }
    }
    public void validate(Object target, Errors errors, int id) {
        // Для проверки на edit (ошибка уникальности, даже если это один и тот же человек)
        Person person = (Person) target;
        if(!peopleService.checkEditUniqueValidation(person, id)){
            rejectValue(errors);
        }
    }
    public void rejectValue(Errors errors){
        errors.rejectValue("name", "", "This name is not unique. Create another one!");
    }
}
