package com.thoughtworks.agenciacompromisso;

import com.thoughtworks.agenciacompromisso.builders.CandidateBuilder;
import com.thoughtworks.agenciacompromisso.models.Candidate;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.repositories.CandidateRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@RunWith(SpringRunner.class)
public class IntegrationTest {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private MockMvc mockMvc;
    private Candidate candidate;

    private CandidateBuilder candidateBuilder;

    @BeforeEach
    public void setUp() {
        candidateBuilder = new CandidateBuilder();

        candidate = new Candidate();
        candidate.setName("Maria");
        candidate.setPhoneNumber("51999111111");
        candidate.setGenderExpression(GenderExpression.FEMALE);
        candidate.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0,"M", 42,40));
    }

    @DisplayName("given new candidate is registered when saving object in db then object is saved with an id")
    @Test
    public void testSave() {
        candidateRepository.save(candidate);

        List<Candidate> returnedList = candidateRepository.findAll();
        assertThat(returnedList.get(0).getId()).isNotEmpty();
        assertThat(candidateRepository.findAll().size()).isEqualTo(1);
    }

    @DisplayName("should find candidate by id and return all information")
    @Test
    public void testFindById() {
        candidateRepository.save(candidate);
        List<Candidate> returnedList = candidateRepository.findAll();
        String id = returnedList.get(0).getId();

        Candidate candidateReturned = candidateRepository.findById(new ObjectId(id));

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

        candidateRepository.save(candidate);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Candidate> returnedList = candidateRepository.findByName(candidate.getName(), pageable);

        Candidate candidateReturned = returnedList.get().findFirst().orElse(new Candidate());

        assertThat(returnedList.getTotalElements()).isEqualTo(1);
        assertThat(candidateReturned.getName()).isEqualTo(candidate.getName());
        assertThat(candidateReturned.getPhoneNumber()).isEqualTo(candidate.getPhoneNumber());
        assertThat(candidateReturned.getGenderExpression()).isEqualTo(candidate.getGenderExpression());
        assertThat(candidateReturned.getSizes().getHeight()).isEqualTo(candidate.getSizes().getHeight());
    }

    @DisplayName("Should delete candidate")
    @Test
    public void testDeleteCandidate() throws Exception {
        Candidate candidate = candidateRepository.save(candidateBuilder.build());

        assertThat(candidateRepository.findById(candidate.getId())).isNotEmpty();

        System.out.println(mockMvc);
        MockHttpServletRequestBuilder request = delete(String.format("/candidate/%s", candidate.getId()));

        this.mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThat(candidateRepository.findById(candidate.getId())).isEmpty();
    }
}
