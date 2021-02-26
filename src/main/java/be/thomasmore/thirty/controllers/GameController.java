package be.thomasmore.thirty.controllers;

import be.thomasmore.thirty.helpers.ImageWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    private ImageWriter imageWriter = new ImageWriter();

    @GetMapping("game")
    public String game(Model model) {
        imageWriter.writeImage();
        return "game";
    }
}