package com.thoughtworks.agenciacompromisso.repositories;

import com.thoughtworks.agenciacompromisso.models.Candidate;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CandidateRepository extends MongoRepository<Candidate, String> {

    @Query("{ 'id' : ?0}")
    Candidate findById(ObjectId id);

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    Page<Candidate> findByName(String name, Pageable pageable);
}
