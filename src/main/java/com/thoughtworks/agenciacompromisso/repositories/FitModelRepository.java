package com.thoughtworks.agenciacompromisso.repositories;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FitModelRepository extends MongoRepository<FitModel, String> {

    @Query("{ 'id' : ?0}")
    FitModel findById(ObjectId id);

}
