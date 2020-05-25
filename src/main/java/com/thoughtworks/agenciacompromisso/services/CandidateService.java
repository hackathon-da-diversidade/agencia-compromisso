package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.models.Candidate;
import com.thoughtworks.agenciacompromisso.repositories.CandidateRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    private CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Page<Candidate> findAllPage(Pageable pageable) {
        return candidateRepository.findAll(pageable);
    }

    public Candidate create(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    public Candidate get(ObjectId id) {
        return candidateRepository.findById(id);
    }

    public Candidate update(Candidate updatedCandidate, ObjectId id) {
        updatedCandidate.setId(id.toString());
        return candidateRepository.save(updatedCandidate);
    }

    public Page<Candidate> search(String name, Pageable pageable) {
        return candidateRepository.findByName(name, pageable);
    }
}
