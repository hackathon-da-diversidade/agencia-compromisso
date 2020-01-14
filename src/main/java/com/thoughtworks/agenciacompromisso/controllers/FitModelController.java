package com.thoughtworks.agenciacompromisso.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.View;
import com.thoughtworks.agenciacompromisso.services.FitModelService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fit-model")

public class FitModelController {

    @Autowired
    private FitModelService fitModelService;


    @GetMapping
    @JsonView(View.List.class)
    public ResponseEntity getAll() {
        List<FitModel> fitModelList = fitModelService.getAll();
        return ResponseEntity.ok().body(fitModelList);
    }

    @PostMapping
    public ResponseEntity create(UriComponentsBuilder uriComponentsBuilder, @RequestBody @Valid FitModel fitModel) {

        FitModel createdFitModel = fitModelService.create(fitModel);
        UriComponents uriComponents = uriComponentsBuilder.path("/fit-model/{id}").buildAndExpand(createdFitModel.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @RequestMapping("/{id}")
    @GetMapping
    public ResponseEntity get(@PathVariable("id") ObjectId id) {
        FitModel fitModel = fitModelService.get(id);
        return ResponseEntity.ok().body(fitModel);
    }

}

