package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String guess(){
        return "guessNum";
    }
    @PostMapping("/roll-dice")
    public String guessPost(@RequestParam("roll") int number){
        return "redirect:/roll-dice/"+number;
    }
    @GetMapping("/roll-dice/{number}")
    @ResponseBody
    public String result(@PathVariable("number") int number) {
        double numResult = Math.floor(Math.random() * 6 + 1);
        if (number == numResult) {
            return "Good guess! You rolled a " + numResult + "!";
        }
        return String.format("Better luck next time.\nYou guessed "+ number+" and rolled a "+ numResult);
    }
}
