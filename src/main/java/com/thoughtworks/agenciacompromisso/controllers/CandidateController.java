package com.thoughtworks.agenciacompromisso.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.thoughtworks.agenciacompromisso.models.Candidate;
import com.thoughtworks.agenciacompromisso.models.View;
import com.thoughtworks.agenciacompromisso.services.CandidateService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;


    @GetMapping
    @JsonView(View.List.class)
    public ResponseEntity getAll() {
        List<Candidate> candidateList = candidateService.getAll();
        return ResponseEntity.ok().body(candidateList);
    }

    @GetMapping("search")
    public Page<Candidate> search(Pageable pageable, @RequestParam("name") String name) {
        return candidateService.search(name, pageable);
    }

    @PostMapping
    public ResponseEntity create(UriComponentsBuilder uriComponentsBuilder, @RequestBody @Valid Candidate candidate) {

        Candidate createdCandidate = candidateService.create(candidate);
        UriComponents uriComponents = uriComponentsBuilder.path("/candidate/{id}").buildAndExpand(createdCandidate.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") ObjectId id) {
        Candidate candidate = candidateService.get(id);
        return ResponseEntity.ok().body(candidate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Candidate> update(@PathVariable("id") ObjectId id, @RequestBody Candidate updatedCandidate) {
        Candidate candidate = candidateService.get(id);

        if (!candidate.getName().isEmpty()) {
            BeanUtils.copyProperties(updatedCandidate, candidate);

            Candidate res = candidateService.update(candidate, id);
            return ResponseEntity.ok().body(res);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/paginated")
    public Page<Candidate> loadCandidatePage(Pageable pageable) {
        return candidateService.findAllPage(pageable);
    }

}

