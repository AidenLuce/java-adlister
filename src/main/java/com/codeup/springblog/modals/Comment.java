package com.codeup.springblog.modals;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {

    /** TABLE COLUMNS */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 50 )
    private String CommentSec;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private post post;


    /** GETTERS & SETTERS */

    public long getId(){return id;}
    public void setId(long id) {this.id = id;}

    public String getCommentSec(){return CommentSec;}
    public void setCommentSec(String commentSec){CommentSec = commentSec;}

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public com.codeup.springblog.modals.post getPost() {
        return post;
    }

    public void setPost(com.codeup.springblog.modals.post post) {
        this.post = post;
    }

    /** CONSTRUCTORS */
    public Comment(){}

    public Comment(long id, String CommentSec){ // MIGHT BE USELESS
        this.id = id;
        this.CommentSec = CommentSec;
    }
    public Comment(String CommentSec, User user){
        this.CommentSec = CommentSec;
        this.user = user;
    }
    public Comment(long id, User user){ // MIGHT BE USELESS
        this.id = id;
        this.user = user;
    }

    public Comment(long id, String CommentSec, User user){
        this.id = id;
        this.CommentSec = CommentSec;
        this.user = user;
    }


}
