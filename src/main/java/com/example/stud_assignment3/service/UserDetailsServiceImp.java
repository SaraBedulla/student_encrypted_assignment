package com.example.stud_assignment3.service;

import com.example.stud_assignment3.repository.User5Repository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final User5Repository user5Repository;

    public UserDetailsServiceImp(User5Repository user5Repository) {
        this.user5Repository = user5Repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return user5Repository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

}
