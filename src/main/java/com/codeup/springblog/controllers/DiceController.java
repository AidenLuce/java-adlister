package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String guess(){
        return "guessNum";
    }
    @GetMapping("/roll-dice/n")
    public String result(){

    }


}
