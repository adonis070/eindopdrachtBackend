package com.huisarts.demo.service;

import com.huisarts.demo.exceptions.ForbiddenException;
import com.huisarts.demo.exceptions.RecordNotFoundException;
import com.huisarts.demo.exceptions.UsernameNotFoundException;
import com.huisarts.demo.repository.UserRepository;
import com.huisarts.demo.model.Autorisatie;
import com.huisarts.demo.model.User;
import com.huisarts.demo.utils.RandomStringGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    //Methode om de huidige User te checken----------------------------------------------------------

    private Authentication getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        else {
            return null;
        }
    }

    private boolean currentUserHasFullAccessToAllUsers() {
        Authentication currentUser = getCurrentUser();
        if (currentUser == null) return false;
        return currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    private boolean currentUserHasAccessToUser(String username) {
        Authentication currentUser = getCurrentUser();
        if (currentUser == null) return false;
        if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return true;
        }
        else {
            return currentUser.getName().equalsIgnoreCase(username);
        }
    }

    @Override
    public Collection<User> getUsers() {
        if (currentUserHasFullAccessToAllUsers()) {
            return userRepository.findAll();
        }
        else {
            if (getCurrentUser() == null) { return new ArrayList<>(); }
            return userRepository.findAllByUsername(getCurrentUser().getName());
        }
    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        if (!currentUserHasAccessToUser(username)) { throw new ForbiddenException(); }
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean userExists(String username) {
        if (!currentUserHasAccessToUser(username)) { throw new ForbiddenException(); }
        return userRepository.existsById(username);
    }

    @Override
    public String createUser(User user) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        user.setApikey(randomString);
        String password = user.getPassword();
        String encoded = passwordEncoder.encode(password);
        user.setPassword(encoded);
        User newUser = userRepository.save(user);
        return newUser.getUsername();
    }

    @Override
    public void deleteUser(String username) {
        if (!currentUserHasAccessToUser(username)) { throw new ForbiddenException(); }
        userRepository.deleteById(username);
    }

    @Override
    public void updateUser(String username, User newUser) {
        if (!currentUserHasAccessToUser(username)) { throw new ForbiddenException(); }
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Set<Autorisatie> getAutorisaties(String username) {
        if (!currentUserHasAccessToUser(username)) { throw new ForbiddenException(); }
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        return user.getAutorisaties();
    }

    @Override
    public void addAutorisatie(String username, String autorisatie) {
        if (!currentUserHasAccessToUser(username)) { throw new ForbiddenException(); }
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAutorisatie(new Autorisatie(username, autorisatie));
        userRepository.save(user);
    }

    @Override
    public void removeAutorisatie(String username, String autorisatie) {
        if (!currentUserHasAccessToUser(username)) { throw new ForbiddenException(); }
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Autorisatie autorisatieToRemove = user.getAutorisaties().stream().filter((a) -> a.getAutorisatie().equalsIgnoreCase(autorisatie)).findAny().get();
        user.removeAutorisatie(autorisatieToRemove);
        userRepository.save(user);
    }

}
