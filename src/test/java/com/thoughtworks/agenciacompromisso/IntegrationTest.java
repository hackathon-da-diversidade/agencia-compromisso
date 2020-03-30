package com.thoughtworks.agenciacompromisso;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.repositories.FitModelRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@ExtendWith(SpringExtension.class)
public class IntegrationTest {

    @Autowired
    private FitModelRepository repository;

    private FitModel fitModel;

    @BeforeEach
    public void setUp() {
        fitModel = new FitModel();
        fitModel.setName("Maria");
        fitModel.setPhoneNumber("51999111111");
        fitModel.setGenderExpression(GenderExpression.FEMALE);
        fitModel.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0, "M", 42,40));
    }

    @DisplayName("given new fit-model is registered when saving object in db then object is saved with an id")
    @Test
    public void testSave() {
        repository.save(fitModel);

        List<FitModel> returnedList = repository.findAll();
        assertThat(returnedList.get(0).getId()).isNotEmpty();
        assertThat(repository.findAll().size()).isEqualTo(1);
    }

    @DisplayName("should find fit-model by id and return all information")
    @Test
    public void testFindById() {
        repository.save(fitModel);
        List<FitModel> returnedList = repository.findAll();
        String id = returnedList.get(0).getId();

        FitModel fitModelReturned = repository.findById(new ObjectId(id));

        assertThat(fitModelReturned.getName()).isEqualTo(fitModel.getName());
        assertThat(fitModelReturned.getPhoneNumber()).isEqualTo(fitModel.getPhoneNumber());
        assertThat(fitModelReturned.getGenderExpression()).isEqualTo(fitModel.getGenderExpression());
        assertThat(fitModelReturned.getSizes().getHeight()).isEqualTo(fitModel.getSizes().getHeight());

    }

    @DisplayName("should find fit-model by name and return all information")
    @Test
    public void testFindByName() {
        String name = "Name";

        fitModel.setName(name);

        repository.save(fitModel);

        List<FitModel> returnedList = repository.findByName(name);

        FitModel fitModelReturned = returnedList.get(0);

        assertThat(returnedList.size()).isEqualTo(1);
        assertThat(fitModelReturned.getName()).isEqualTo(name);
        assertThat(fitModelReturned.getPhoneNumber()).isEqualTo(fitModel.getPhoneNumber());
        assertThat(fitModelReturned.getGenderExpression()).isEqualTo(fitModel.getGenderExpression());
        assertThat(fitModelReturned.getSizes().getHeight()).isEqualTo(fitModel.getSizes().getHeight());
    }
}
