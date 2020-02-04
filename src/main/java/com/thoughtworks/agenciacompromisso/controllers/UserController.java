package com.thoughtworks.agenciacompromisso.controllers;

import com.thoughtworks.agenciacompromisso.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authorize")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{email}")
    @GetMapping
    public ResponseEntity authorize(@PathVariable("email") String email) {
        Boolean isAuthorized = userService.isAuthorized(email);
        if (isAuthorized) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "http://localhost:3000/menu");
            return ResponseEntity.ok().headers(headers).build();
        }
        return ResponseEntity.notFound().build();
    }

}
