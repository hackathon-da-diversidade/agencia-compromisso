package com.thoughtworks.agenciacompromisso.controllers;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.services.FitModelService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fit-model/{id}")
public class FitModelProfileController {

    @Autowired
    private FitModelService fitModelService;

    @GetMapping
    public ResponseEntity get(@PathVariable("id") ObjectId id) {
        FitModel fitModel = fitModelService.get(id);
        return ResponseEntity.ok().body(fitModel);
    }

}
