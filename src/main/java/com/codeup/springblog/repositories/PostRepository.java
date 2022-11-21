package com.codeup.springblog.repositories;

import com.codeup.springblog.modals.post;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostRepository extends JpaRepository<post, Long> {

}
