package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping
    @ResponseBody
    public String showAll(){
        return "posts index page";
    }

    @GetMapping ("/{id}")
    @ResponseBody
    public String viewById(@PathVariable String id){
        return "view an individual post";
    }

    @GetMapping ("/create")
    @ResponseBody
    public String viewById(){
        return "view the form for creating a post";
    }

    @RequestMapping (path ="/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(){
        return "create a new post";
    }
}
