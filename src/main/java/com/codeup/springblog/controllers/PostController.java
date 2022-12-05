package com.codeup.springblog.controllers;

import com.codeup.springblog.modals.User;
import com.codeup.springblog.modals.post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostRepository PostDao;
    private final UserRepository UserDao;

    private final EmailService emailService;
    public PostController(PostRepository PostDao, UserRepository userDao, EmailService emailService){
        this.PostDao = PostDao;
        this.UserDao = userDao;
        this.emailService = emailService;
    }

//    http://localhost:8080/posts/new/post
    @GetMapping("/new/post")
    public String title(){
        return "posts";
    }

//    @GetMapping("/{id}")
//    public String onePost(@PathVariable long id, Model model){
//        post post = PostDao.findById(id);
//        model.addAttribute("post", post);
//        return "/posts/show";
//    }

//    @PostMapping("/new")
//    public String postPost(@RequestParam(name="title")String title, @RequestParam(name="body")String body){
//        post postCard = new post(title,body);
//        if(!postCard.getTitle().equals("") && !postCard.getBody().equals("")) {
//            User user  = UserDao.getById(1L);
//            postCard.setUser(user);
//            PostDao.save(postCard);
//            return "redirect:/posts/allPosts";
//        }
//        return "posts";
//    }

    @PostMapping("/new")
    public String newPost(@ModelAttribute post post){
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(); // this
            User user  = UserDao.getById(currentUserId);
            post.setUser(user);
            PostDao.save(post);
            emailService.prepareAndSend(user, post.getTitle(), post.getBody());
            return "redirect:/posts/allPosts";
        }



    @GetMapping("/allPosts")
    public String all(Model model){
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        User user = UserDao.getById(currentUserId);
        model.addAttribute("user",user);
        model.addAttribute("post",new post());
        List<post> posts = PostDao.findAll();
        model.addAttribute("posts", posts);

        for(post post: posts){

            if (post.getColor() == null || post.getColor() != null){
                return "posts";}

        }
        return "posts";
    }



    @GetMapping ("/index")
    public String allPosts(Model model){
        post post1 = new post(1,"First","this is the body");
        post post2 = new post(2,"Second","this is the body");
        List<post>  allPosts = new ArrayList<>(List.of(post1, post2));
        model.addAttribute("allPosts",allPosts);
        return "index";
    }

    @GetMapping ("/show/{id}")
    public String viewById(@PathVariable long id, Model model){
      post post = PostDao.getById(id);
        model.addAttribute("post", post);
        return "/show";
    }

    @GetMapping ("/create")
    @ResponseBody
    public String create(){
        return "Go to http://localhost:8080/posts/new/post to view the form for creating a post.";
    }

    @GetMapping("/{id}/edit")
    public String editPostForm(@PathVariable long id, Model model){
        post post = PostDao.getById(id);
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public  String edit(@ModelAttribute post post){
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(); // this
        User user = UserDao.getById(currentUserId);
        post.setUser(user);
        PostDao.save(post);
        return "redirect:/posts/allPosts";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable long id){
        post post = PostDao.getById(id);
        PostDao.delete(post);
        return "redirect:/posts/allPosts";
    }



}


