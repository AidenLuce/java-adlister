package com.codeup.springblog.modals;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Repo_Jpa_database")
public class post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 25)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false, length = 250)
    private String body;

    @OneToMany(mappedBy="post")
    List<Comment> commentList;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public post (){}

    public post(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public post (long id, String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
    }
}



//

