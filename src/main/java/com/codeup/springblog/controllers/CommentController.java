package com.codeup.springblog.controllers;

import com.codeup.springblog.modals.Comment;
import com.codeup.springblog.modals.post;
import com.codeup.springblog.repositories.CommentRepository;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private final PostRepository PostDao;
    private final UserRepository UserDao;

    private final CommentRepository CommentDao;

    public CommentController(PostRepository PostDao, UserRepository UserDao, CommentRepository CommentDao){
        this.PostDao = PostDao;
        this.UserDao = UserDao;
        this.CommentDao = CommentDao;
    }

    @GetMapping("/posts/{id}/comment/{user_id}")
    public String commentForm(@PathVariable long id, Model model){
        post post = PostDao.getById(id);
        model.addAttribute("comment", new Comment());
        model.addAttribute("post", post);
        return "comment";
    }

    @PostMapping("/posts/{id}/comment/{user_id}")
        public String newComment(@PathVariable long user_id, @PathVariable long id,@ModelAttribute Comment comment){
        comment.setUser(UserDao.getById(user_id));
        comment.setPost(PostDao.getById(id));
        CommentDao.save(comment);
        return "redirect:/posts/allPosts";
    }
}
