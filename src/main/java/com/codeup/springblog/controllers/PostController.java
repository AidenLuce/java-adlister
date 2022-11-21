package com.codeup.springblog.controllers;

import com.codeup.springblog.modals.post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostRepository PostDao;
    public PostController(PostRepository PostDao){
        this.PostDao = PostDao;
    }

//    http://localhost:8080/posts/new/post
    @GetMapping("/new/post")
    public String title(){
        return "posts";
    }

    @PostMapping("/new")
    public String newPost(@RequestParam(name="title")String title, @RequestParam(name="body")String body){
        post postCard = new post(title,body);
        PostDao.save(postCard);
        return "posts";
    }
    @GetMapping("/allPosts")
    public String allCoffees(Model model){
        List<post> posts = PostDao.findAll();
        model.addAttribute("posts", posts);
        return "allPosts";
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
        post post1 = new post(1,"First","this is the body");
        post post2 = new post(2,"Second","this is the body");
        post post3 = new post(3,"yo","wassup?");
        List<post> allPosts = new ArrayList<>(List.of(post1, post2,post3));
        model.addAttribute("postId", id);
        post post = null;
        for (post userPost : allPosts){
            if(userPost.getId() == id){
                post = userPost;
            }
        }
        model.addAttribute("post", post);
        return "/show";
    }

    @GetMapping ("/create")
    @ResponseBody
    public String create(){
        return "view the form for creating a post";
    }
}
