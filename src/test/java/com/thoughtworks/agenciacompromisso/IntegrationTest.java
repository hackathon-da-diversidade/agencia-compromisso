package com.thoughtworks.agenciacompromisso;

import com.thoughtworks.agenciacompromisso.builders.CandidateBuilder;
import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.repositories.FitModelRepository;
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
    private FitModelRepository candidateRepository;

    @Autowired
    private MockMvc mockMvc;

    private FitModel fitModel;

    private CandidateBuilder candidateBuilder;

    @BeforeEach
    public void setUp() {
        candidateBuilder = new CandidateBuilder();

        fitModel = new FitModel();
        fitModel.setName("Maria");
        fitModel.setPhoneNumber("51999111111");
        fitModel.setGenderExpression(GenderExpression.FEMALE);
        fitModel.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0, "M", 42, 40));
    }

    @DisplayName("given new fit-model is registered when saving object in db then object is saved with an id")
    @Test
    public void testSave() {
        candidateRepository.save(fitModel);

        List<FitModel> returnedList = candidateRepository.findAll();
        assertThat(returnedList.get(0).getId()).isNotEmpty();
        assertThat(candidateRepository.findAll().size()).isEqualTo(1);
    }

    @DisplayName("should find fit-model by id and return all information")
    @Test
    public void testFindById() {
        candidateRepository.save(fitModel);
        List<FitModel> returnedList = candidateRepository.findAll();
        String id = returnedList.get(0).getId();

        FitModel fitModelReturned = candidateRepository.findById(new ObjectId(id));

        assertThat(fitModelReturned.getName()).isEqualTo(fitModel.getName());
        assertThat(fitModelReturned.getPhoneNumber()).isEqualTo(fitModel.getPhoneNumber());
        assertThat(fitModelReturned.getGenderExpression()).isEqualTo(fitModel.getGenderExpression());
        assertThat(fitModelReturned.getSizes().getHeight()).isEqualTo(fitModel.getSizes().getHeight());
        assertThat(fitModelReturned.getSizes().getShirtSize()).isEqualTo(fitModel.getSizes().getShirtSize());

    }

    @DisplayName("should find fit-model by name and return all information")
    @Test
    public void testFindByName() {
        String name = "Name";

        fitModel.setName(name);

        candidateRepository.save(fitModel);

        Pageable pageable = PageRequest.of(0, 10);

        Page<FitModel> returnedList = candidateRepository.findByName(fitModel.getName(), pageable);

        FitModel fitModelReturned = returnedList.get().findFirst().orElse(new FitModel());

        assertThat(returnedList.getTotalElements()).isEqualTo(1);
        assertThat(fitModelReturned.getName()).isEqualTo(fitModel.getName());
        assertThat(fitModelReturned.getPhoneNumber()).isEqualTo(fitModel.getPhoneNumber());
        assertThat(fitModelReturned.getGenderExpression()).isEqualTo(fitModel.getGenderExpression());
        assertThat(fitModelReturned.getSizes().getHeight()).isEqualTo(fitModel.getSizes().getHeight());
    }

    @DisplayName("Should delete candidate")
    @Test
    public void testDeleteCandidate() throws Exception {
        FitModel candidate = candidateRepository.save(candidateBuilder.build());

        assertThat(candidateRepository.findById(candidate.getId())).isNotEmpty();

        MockHttpServletRequestBuilder request = delete(String.format("/fit-model/%s", candidate.getId()));

        this.mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThat(candidateRepository.findById(candidate.getId())).isEmpty();
    }
}
