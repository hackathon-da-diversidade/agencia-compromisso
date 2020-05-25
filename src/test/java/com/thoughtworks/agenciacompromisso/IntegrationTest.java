package com.thoughtworks.agenciacompromisso;

import com.thoughtworks.agenciacompromisso.models.Candidate;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.repositories.CandidateRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@ExtendWith(SpringExtension.class)
public class IntegrationTest {

    @Autowired
    private CandidateRepository repository;

    private Candidate candidate;

    @BeforeEach
    public void setUp() {
        candidate = new Candidate();
        candidate.setName("Maria");
        candidate.setPhoneNumber("51999111111");
        candidate.setGenderExpression(GenderExpression.FEMALE);
        candidate.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0,"M", 42,40));
    }

    @DisplayName("given new candidate is registered when saving object in db then object is saved with an id")
    @Test
    public void testSave() {
        repository.save(candidate);

        List<Candidate> returnedList = repository.findAll();
        assertThat(returnedList.get(0).getId()).isNotEmpty();
        assertThat(repository.findAll().size()).isEqualTo(1);
    }

    @DisplayName("should find candidate by id and return all information")
    @Test
    public void testFindById() {
        repository.save(candidate);
        List<Candidate> returnedList = repository.findAll();
        String id = returnedList.get(0).getId();

        Candidate candidateReturned = repository.findById(new ObjectId(id));

        assertThat(candidateReturned.getName()).isEqualTo(candidate.getName());
        assertThat(candidateReturned.getPhoneNumber()).isEqualTo(candidate.getPhoneNumber());
        assertThat(candidateReturned.getGenderExpression()).isEqualTo(candidate.getGenderExpression());
        assertThat(candidateReturned.getSizes().getHeight()).isEqualTo(candidate.getSizes().getHeight());
        assertThat(candidateReturned.getSizes().getShirtSize()).isEqualTo(candidate.getSizes().getShirtSize());

    }

    @DisplayName("should find candidate by name and return all information")
    @Test
    public void testFindByName() {
        String name = "Name";

        candidate.setName(name);

        repository.save(candidate);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Candidate> returnedList = repository.findByName(candidate.getName(), pageable);

        Candidate candidateReturned = returnedList.get().findFirst().orElse(new Candidate());

        assertThat(returnedList.getTotalElements()).isEqualTo(1);
        assertThat(candidateReturned.getName()).isEqualTo(candidate.getName());
        assertThat(candidateReturned.getPhoneNumber()).isEqualTo(candidate.getPhoneNumber());
        assertThat(candidateReturned.getGenderExpression()).isEqualTo(candidate.getGenderExpression());
        assertThat(candidateReturned.getSizes().getHeight()).isEqualTo(candidate.getSizes().getHeight());
    }
}
