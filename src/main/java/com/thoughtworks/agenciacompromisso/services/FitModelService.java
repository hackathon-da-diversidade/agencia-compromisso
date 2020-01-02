package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.repositories.FitModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitModelService {
    private FitModelRepository fitModelRepository;

    public FitModelService(FitModelRepository fitModelRepository) {
        this.fitModelRepository = fitModelRepository;
    }

    public FitModel create(FitModel fitModel) {
        return fitModelRepository.save(fitModel);
    }

    public List<FitModel> getAll() {
        return fitModelRepository.findAll();
    }
}
