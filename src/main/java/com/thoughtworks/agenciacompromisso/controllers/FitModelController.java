package com.thoughtworks.agenciacompromisso.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/fit-model")

public class FitModelController {

    @PostMapping
    public ResponseEntity create(UriComponentsBuilder uriComponentsBuilder) {
        UriComponents uriComponents = uriComponentsBuilder.path("/fit-model/{id}").buildAndExpand("2");
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

}
