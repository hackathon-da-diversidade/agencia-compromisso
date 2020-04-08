package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.repositories.FitModelRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitModelService {
    private FitModelRepository fitModelRepository;

    public FitModelService(FitModelRepository fitModelRepository) {
        this.fitModelRepository = fitModelRepository;
    }

    public Page<FitModel> findAllPage(Pageable pageable) {
        return fitModelRepository.findAll(pageable);
    }

    public FitModel create(FitModel fitModel) {
        return fitModelRepository.save(fitModel);
    }

    public List<FitModel> getAll() {
        return fitModelRepository.findAll();
    }

    public FitModel get(ObjectId id) {
        return fitModelRepository.findById(id);
    }

    public FitModel update(FitModel updatedFitModel, ObjectId id) {
        updatedFitModel.setId(id.toString());
        return fitModelRepository.save(updatedFitModel);
    }

    public Page<FitModel> search(String name, Pageable pageable) {
        return fitModelRepository.findByName(name, pageable);
    }
}
