package com.example.auth.controllers;

import com.example.auth.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("api/validate")
public class ValidateController {

    // Really simple, if the request makes it this far we can return the "User" object
    @GetMapping
    public User validateUser(@SessionAttribute User user) {
        System.out.println(user);
        return user;
    }
}
