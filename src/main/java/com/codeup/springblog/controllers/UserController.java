package com.codeup.springblog.controllers;

import com.codeup.springblog.modals.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {
    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;



    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder ){
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

public String RegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "/registration";
}
public String registerUser(@ModelAttribute User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersDao.save(user);
        return "redirect:/posts/allPosts";
}
}
