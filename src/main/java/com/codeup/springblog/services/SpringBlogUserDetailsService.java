package com.codeup.springblog.services;

import com.codeup.springblog.modals.SpringBlogUserDetails;
import com.codeup.springblog.modals.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringBlogUserDetailsService implements UserDetailsService {

    public final UserRepository userDao;

    public SpringBlogUserDetailsService(UserRepository userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username + " not found");
        } else {
            return  new SpringBlogUserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        }
    }
}
