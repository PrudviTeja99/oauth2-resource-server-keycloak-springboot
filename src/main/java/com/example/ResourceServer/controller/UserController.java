package com.example.ResourceServer.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/test")
    @PreAuthorize("hasRole('client_user')")
    public String getUser(){
        return "Hello Client user";
    }
}
