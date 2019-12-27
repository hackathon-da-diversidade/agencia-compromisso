package com.thoughtworks.agenciacompromisso.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.services.FitModelService;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FitModelController.class)
public class FitModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FitModelService fitModelService;

    @Test
    public void shouldReturnStatusCode201AndLocationHeaderWhenPostingFitModel() throws Exception {
        FitModel fitModel = new FitModel();
        fitModel.setName("Maria");
        fitModel.setPhoneNumber("51999111111");
        fitModel.setGenderExpression(GenderExpression.FEMALE);
        fitModel.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0));

        FitModel fitModelReturned = new FitModel();
        fitModelReturned.setId(new ObjectId());

        when(fitModelService.create(any(FitModel.class))).thenReturn(fitModelReturned);

        mockMvc.perform(
                post("/fit-model")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fitModel)))
                        .andExpect(header().string("location", containsString("fit-model/" + fitModelReturned.getId())))
                        .andExpect(status().isCreated());
    }
}
