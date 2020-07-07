package ru.ulxanxv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(@RequestParam(value = "name", defaultValue = "NO NAME") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")
    public String about( Model model) {
        model.addAttribute("title", "Про нас");
        return "about";
    }

}
