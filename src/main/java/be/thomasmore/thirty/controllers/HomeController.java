package be.thomasmore.thirty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {
    @GetMapping({"/", "home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}