package com.codeup.springblog.controllers;

import com.codeup.springblog.modals.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {
    private final UserRepository usersDao;

    public UserController(UserRepository usersDao){
        this.usersDao = usersDao;
    }

public String RegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "/registration";
}
public String registerUser(@ModelAttribute User user){
        usersDao.save(user);
        return "redirect:/posts/allPosts";
}
}
