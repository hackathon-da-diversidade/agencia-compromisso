package com.thoughtworks.agenciacompromisso.controllers;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.services.FitModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/fit-model")

public class FitModelController {

    @Autowired
    private FitModelService fitModelService;

    @PostMapping
    public ResponseEntity create(UriComponentsBuilder uriComponentsBuilder, @Valid FitModel fitModel) {

        FitModel createdFitModel = fitModelService.create(fitModel);

        UriComponents uriComponents = uriComponentsBuilder.path("/fit-model/{id}").buildAndExpand(createdFitModel.getId());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

}
