package com.huisarts.demo.service;

import com.huisarts.demo.model.Autorisatie;
import com.huisarts.demo.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Collection<User> getUsers();
    Optional<User> getUser(String username);
    String createUser(User user);
    void updateUser(String username, User user);
    void deleteUser(String username);
    Optional<User> getUserByUsername(String username);
    boolean userExists(String username);
    Set<Autorisatie> getAutorisaties(String username);
    void addAutorisatie(String username, String autorisatie);
    void removeAutorisatie(String username, String autorisatie);

}
