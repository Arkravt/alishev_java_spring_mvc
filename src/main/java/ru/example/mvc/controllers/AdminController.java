package ru.example.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.mvc.dao.PersonDao_JdbcTemplate;
import ru.example.mvc.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private PersonDao_JdbcTemplate personDao;

    @Autowired
    public AdminController(PersonDao_JdbcTemplate personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDao.index());
        return "admin/adminPage";
    }

    @PatchMapping("add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        System.out.println(person.getId());
        return "redirect:/people";
    }

}
