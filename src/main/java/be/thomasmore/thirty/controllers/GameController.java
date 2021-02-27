package be.thomasmore.thirty.controllers;

import be.thomasmore.thirty.gameLogic.Board;
import be.thomasmore.thirty.helpers.CoorParser;
import be.thomasmore.thirty.helpers.ImageWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.Collection;
import java.util.Map;

//https://stackoverflow.com/questions/34896877/call-controller-method-from-jsp-button-in-spring-mvc
@Controller
public class GameController {
    private Board board;
    private int imgSize = 20;
    private boolean isFirstRun = true;

    //private ImageWriter imageWriter = new ImageWriter();

    /*@GetMapping("game")
    public String game(Model model) {
        ImageWriter.writeImage(imgSize, imgSize);
        Board board = new Board();
        ClickHandler clickHandler = new ClickHandler();
        model.addAttribute(board);
        model.addAttribute(clickHandler);
        model.addAttribute("tileSize", imgSize);
        return "game";
    }*/

    @GetMapping("game")
    public String game(Model model) {
        onFirstRun();
        /*board = new Board();
        try {
            Point location = CoorParser.parse(locationString);
            System.out.print(location);
        } catch (Exception e){

        }*/
        model.addAttribute(board);;
        model.addAttribute("tileSize", imgSize);
        return "game";
    }

    private void onFirstRun(){
        if(isFirstRun){
            //ImageWriter.writeImage(imgSize, imgSize);
            board = new Board();

            isFirstRun = false;
        }
    }

    /*
    call function from javascript

    @GetMapping(value="/start")
    public String start(@RequestParam("x") float rawX, @RequestParam("y") float rawY){
        int x = (int)rawX;
        int y = (int)rawY;
        System.out.print("clicked tile:" + x + "_" + y);
        return "game";
    }*/
}