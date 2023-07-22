package com.huisarts.demo.service;

import com.huisarts.demo.model.Autorisatie;
import com.huisarts.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userService.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        String password = user.orElse(null).getPassword();
        String encoded = passwordEncoder.encode(password);
        user.orElse(null).setPassword(encoded);

        Set<Autorisatie> autorisaties = user.orElse(null).getAutorisaties();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Autorisatie autorisatie : autorisaties) {
            grantedAuthorities.add(new SimpleGrantedAuthority(autorisatie.getAutorisatie()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }


}
