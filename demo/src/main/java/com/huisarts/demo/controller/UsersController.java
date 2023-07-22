package com.huisarts.demo.controller;


import com.huisarts.demo.exceptions.BadRequestException;
import com.huisarts.demo.model.User;
import com.huisarts.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/users")
public class UsersController {

    /**
     * private klasse UserService wordt geimporteerd uit de Service map
     * object wordt userService aangemaakt
     * de UserController functie gebruikt het object van de UserService klasse
     * deze waardes worden vervolgens opgeslagen in userRepository
     */


    /**
     * @Autowired is een springframework annotatie die geimporteerd wordt
     * hiermee worden bijbehorden beans opgelost en geinjecteerd door spring om vervolgens samen te werken.
     *  hier is de autowire toegepast op de het object van UserService dat hier gemaakt wordt
     */
    @Autowired
    private UserService userService;


    //CRUD(Create Read Update Delete) Methodes****************************************************


    //Get all user(als je admin bent) ---------------------------------------------------------------------------
    //Als je de rechten van user hebt dan kun je alleen je eigen account zien------------------------------------
    @GetMapping(value = "")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Object> getUser() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    //Get user -----------------------------------------------------------------------------

    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    //Create user---------------------------------------------------------------------------

    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        String newUsername = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    //Update user---------------------------------------------------------------------------

    @PutMapping(value = "/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        userService.updateUser(username, user);
        return ResponseEntity.noContent().build();
    }

    //Partial update klant ---------------------------------------------------------------------------

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }


    //Autorisatie methoden***********************************************************************


    @GetMapping(value = "/{username}/autorisaties")
    public ResponseEntity<Object> getUserAutorisatie(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAutorisaties(username));
    }

    //Autorisatie methoden***********************************************************************

    @PostMapping(value = "/{username}/autorisaties")
    public ResponseEntity<Object> addUserAutorisatie(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String autorisatieName = (String) fields.get("authority");
            userService.addAutorisatie(username, autorisatieName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    //Delete Authorisation to user---------------------------------------------------------------------------

    @DeleteMapping(value = "/{username}/autorisaties/{autorisatie}")
    public ResponseEntity<Object> deleteUserAutorisatie(@PathVariable("username") String username, @PathVariable("autorisatie") String authority) {
        userService.removeAutorisatie(username, authority);
        return ResponseEntity.noContent().build();
    }




}
