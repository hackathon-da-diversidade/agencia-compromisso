package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.repositories.FitModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FitModelServiceTest {
    private FitModelService fitModelService;

    @Mock
    private FitModelRepository fitModelRepository;

    @Test
    public void shouldCreateFitModel() {
        fitModelService = new FitModelService(fitModelRepository);
        FitModel fitModel = new FitModel();
        fitModel.setName("Calvin");

        FitModel fitModelReturned = new FitModel();
        fitModelReturned.setId(1);
        fitModelReturned.setName(fitModel.getName());

        when(fitModelRepository.save(refEq(fitModel))).thenReturn(fitModelReturned);

        FitModel createdFitModel = fitModelService.create(fitModel);
        assertThat(createdFitModel.getName(), is(fitModelReturned.getName()));
        assertThat(createdFitModel.getId(),is(fitModelReturned.getId()));

    }

}