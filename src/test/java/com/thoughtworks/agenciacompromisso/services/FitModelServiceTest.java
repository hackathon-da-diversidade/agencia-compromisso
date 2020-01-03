package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.repositories.FitModelRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FitModelServiceTest {
    private FitModelService fitModelService;
    private FitModel fitModel;

    @Mock
    private FitModelRepository fitModelRepository;

    @Before
    public void setUp() throws Exception {
        fitModelService = new FitModelService(fitModelRepository);
        fitModel = new FitModel();
        fitModel.setName("Maria");
        fitModel.setPhoneNumber("51999111111");
        fitModel.setGenderExpression(GenderExpression.FEMALE);
        fitModel.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0));    }

    @Test
    public void shouldCreateFitModel() {
        FitModel fitModelReturned = new FitModel();
        fitModelReturned.setId(new ObjectId().toString());
        fitModelReturned.setName(fitModel.getName());

        when(fitModelRepository.save(refEq(fitModel))).thenReturn(fitModelReturned);

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
        assertThat(fitModelListReturned.get(1).getName(),is("João"));
    }

}