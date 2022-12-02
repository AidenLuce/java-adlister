package com.codeup.springblog.repositories;

import com.codeup.springblog.modals.Comment;
import com.codeup.springblog.modals.post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment, Long>{
    Comment getById(long id);
}
