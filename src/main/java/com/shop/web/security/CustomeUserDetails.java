package com.shop.web.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.web.models.Users;
import com.shop.web.repository.UserRepository;

@Service
public class CustomeUserDetails implements UserDetailsService{
    private UserRepository userService;

    public CustomeUserDetails(UserRepository userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.findFirstByUsername(username);

        if(user != null){
            User authUser = new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList())
            );
            return authUser;
        }else{
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }


}
