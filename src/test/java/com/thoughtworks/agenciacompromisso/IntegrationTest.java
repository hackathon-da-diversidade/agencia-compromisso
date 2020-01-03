package com.thoughtworks.agenciacompromisso;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.repositories.FitModelRepository;

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

    @DisplayName("given new fit-model is registered when saving object in db then object is saved with an id")
    @Test
    public void testSave() throws Exception {
        FitModel fitModel = new FitModel();
        fitModel.setName("Maria");
        fitModel.setPhoneNumber("51999111111");
        fitModel.setGenderExpression(GenderExpression.FEMALE);
        fitModel.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0));

        repository.save(fitModel);

        List<FitModel> returnedList = repository.findAll();
        assertThat(returnedList.get(0).getId()).isNotEmpty();
        assertThat(repository.findAll().size()).isEqualTo(1);
    }


}
