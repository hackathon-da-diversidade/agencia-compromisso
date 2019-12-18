package com.thoughtworks.agenciacompromisso.controllers;

import com.thoughtworks.agenciacompromisso.models.FitModel;
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

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.refEq;
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
        fitModel.setPhoneNumber("519123392222");

        FitModel fitModelReturned = new FitModel();
        fitModelReturned.setId(new ObjectId());

        when(fitModelService.create(refEq(fitModel))).thenReturn(fitModelReturned);

        mockMvc.perform(
                        post("/fit-model")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("name", fitModel.getName())
                                .param("phoneNumber", fitModel.getPhoneNumber()))
                .andExpect(header().string("location", containsString("fit-model/" + fitModelReturned.getId())))
                .andExpect(status().isCreated());
    }
}
