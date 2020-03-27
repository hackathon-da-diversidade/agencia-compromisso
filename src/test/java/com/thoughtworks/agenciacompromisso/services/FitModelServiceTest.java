package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.repositories.FitModelRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FitModelServiceTest {

    private FitModel fitModel;

    @MockBean
    private FitModelRepository fitModelRepository;

    @Autowired
    private FitModelService fitModelService;

    @BeforeEach
    public void setUp() {
        fitModel = new FitModel();
        fitModel.setName("Maria");
        fitModel.setPhoneNumber("51999111111");
        fitModel.setGenderExpression(GenderExpression.FEMALE);
        fitModel.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0, "M"));
        fitModel.setBirthday(LocalDate.of(1990, 2, 11));

    }

    @Test
    public void shouldCreateFitModel() {
        FitModel fitModelReturned = new FitModel();
        fitModelReturned.setId(new ObjectId().toString());
        fitModelReturned.setName(fitModel.getName());

        when(fitModelRepository.save(any(FitModel.class))).thenReturn(fitModelReturned);

        FitModel createdFitModel = fitModelService.create(fitModel);
        assertThat(createdFitModel.getName(), is(fitModelReturned.getName()));
        assertThat(createdFitModel.getId(), is(fitModelReturned.getId()));

    }

    @Test
    public void shouldReturnAllFitModels() {
        List<FitModel> fitModelList = new ArrayList<>();
        FitModel secondFitModel = new FitModel();
        secondFitModel.setName("João");

        fitModelList.add(fitModel);
        fitModelList.add(secondFitModel);

        when(fitModelRepository.findAll()).thenReturn(fitModelList);

        List<FitModel> fitModelListReturned = fitModelService.getAll();
        assertThat(fitModelListReturned.size(), is(fitModelList.size()));
        assertThat(fitModelListReturned.get(1).getName(), is("João"));
    }

    @Test
    public void shouldGetFitModel() {

        ObjectId id = new ObjectId();
        fitModel.setId(id.toString());

        when(fitModelRepository.findById(id)).thenReturn(fitModel);
        FitModel fitModelReturned = fitModelService.get(id);

        assertThat(fitModelReturned, is(fitModel));

    }

    @Test
    public void shouldUpdateFitModel() {
        ObjectId id = new ObjectId();
        fitModel.setId(id.toString());

        FitModel updatedFitModel = new FitModel();

        BeanUtils.copyProperties(fitModel, updatedFitModel);
        updatedFitModel.setName("Ana Carolina");
        updatedFitModel.setPhoneNumber("58993249582");

        when(fitModelRepository.save(any())).thenReturn(updatedFitModel);

        FitModel fitModelReturned = fitModelService.update(updatedFitModel, id);

        assertThat(fitModelReturned.getName(), is(updatedFitModel.getName()));
        assertThat(fitModelReturned.getPhoneNumber(), is(updatedFitModel.getPhoneNumber()));
        assertThat(fitModelReturned.getId(), is(updatedFitModel.getId()));
    }

}