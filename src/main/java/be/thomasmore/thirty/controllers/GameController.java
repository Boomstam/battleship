package be.thomasmore.thirty.controllers;

import be.thomasmore.thirty.gameLogic.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

//https://stackoverflow.com/questions/34896877/call-controller-method-from-jsp-button-in-spring-mvc
@Controller
public class GameController {
    private Board board;
    private int imgSize = 20;
    private boolean isFirstRun = true;

    private Point currentLocation = new Point(0, 0);

    @GetMapping("game")
    public String game(Model model) {
        onFirstRun();
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

    @GetMapping(value="/start")
    public void start(@RequestParam("x") float rawX, @RequestParam("y") float rawY){
        int x = (int)rawX;
        int y = (int)rawY;
        System.out.print("clicked tile:" + x + "_" + y);
        currentLocation.setLocation(x, y);
    }
}