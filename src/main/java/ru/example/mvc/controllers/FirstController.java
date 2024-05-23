package ru.example.mvc.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "secondName", required = false) String secondName,
                           HttpServletRequest request) {

        System.out.println(name + " " + secondName);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String sayGoodBye() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a,
                             @RequestParam("b") int b,
                             @RequestParam("action") String action,
                             Model model) {

        switch (action) {
            case "multiplication":
                model.addAttribute("res", a * b);
                break;
            case "addition":
                model.addAttribute("res", a + b);
                break;
            case "subtracion":
                model.addAttribute("res", a - b);
                break;
            case "division":
                model.addAttribute("res", a / (double) b);
                break;
            default:
                model.addAttribute("res", "Some thing went wrong");
                break;
        }

        return "first/calculator";
    }

}
