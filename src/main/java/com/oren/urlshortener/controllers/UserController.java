package com.oren.urlshortener.controllers;

import com.oren.urlshortener.exceptions.NotExistException;
import com.oren.urlshortener.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestHeader String username) {
        return new ResponseEntity<>(userService.addUser(username), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String username) throws NotExistException {
        userService.deleteUser(username);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() throws NotExistException {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username) throws NotExistException {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

}
