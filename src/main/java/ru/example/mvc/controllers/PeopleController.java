package ru.example.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.mvc.dao.PersonDao_JDBC_API;
import ru.example.mvc.dao.PersonDao_JdbcTemplate;
import ru.example.mvc.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    //private final PersonDao_JDBC_API personDao;
    private final PersonDao_JdbcTemplate personDao;

    @Autowired
    //public PeopleController(PersonDao_JDBC_API personDao) {
    public PeopleController(PersonDao_JdbcTemplate personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDao.index());
        return "people/index";
    }

    @GetMapping("{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.show(id));
        return "people/show";
    }

    @GetMapping("new")
    public String newPerson(@ModelAttribute("person") Person person) {
        //public String newPerson(Model model) {
        //model.addAttribute(new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        personDao.save(person);
        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.show(id));
        return "people/edit";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDao.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }

}















