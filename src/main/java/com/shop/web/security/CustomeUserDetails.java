package com.shop.web.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.web.models.UserEntity;
import com.shop.web.repository.UserEntityRepository;

@Service
public class CustomeUserDetails implements UserDetailsService{
    private UserEntityRepository userEntityRepository;

    public CustomeUserDetails(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findFirstByUsername(username);

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
