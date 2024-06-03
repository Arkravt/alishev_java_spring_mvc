package ru.example.mvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.mvc.dao.PersonDao_JdbcTemplate;
import ru.example.mvc.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDao_JdbcTemplate personDao;

    @Autowired
    public PersonValidator(PersonDao_JdbcTemplate personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDao.show(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
