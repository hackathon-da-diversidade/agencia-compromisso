package com.thoughtworks.agenciacompromisso.repositories;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FitModelRepository extends MongoRepository<FitModel, String> {
}
