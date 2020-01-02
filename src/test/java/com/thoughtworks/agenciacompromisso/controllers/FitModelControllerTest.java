package com.thoughtworks.agenciacompromisso.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.enums.GenderExpression;
import com.thoughtworks.agenciacompromisso.services.FitModelService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private FitModel fitModel;

    @Before
    public void setUp() throws Exception {
        fitModel = new FitModel();
        fitModel.setName("Maria");
        fitModel.setPhoneNumber("51999111111");
        fitModel.setGenderExpression(GenderExpression.FEMALE);
        fitModel.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0));
    }

    @Test
    public void shouldReturnStatusCode201AndLocationHeaderWhenPostFitModel() throws Exception {
        FitModel fitModelReturned = new FitModel();
        fitModelReturned.setId(new ObjectId().toString());

        when(fitModelService.create(any(FitModel.class))).thenReturn(fitModelReturned);

        mockMvc.perform(
                post("/fit-model")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fitModel)))
                .andExpect(header().string("location", containsString("fit-model/" + fitModelReturned.getId())))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnStatusCode200AndFitModelListWhenGetFitModel() throws Exception {
        List<FitModel> fitModelList = new ArrayList<>();
        String id = new ObjectId().toString();
        fitModel.setId(id);
        fitModelList.add(fitModel);
        fitModelList.add(fitModel);

        when(fitModelService.getAll()).thenReturn(fitModelList);

        MvcResult result = mockMvc.perform(
                get("/fit-model").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content, not(is(new ObjectMapper().writeValueAsString(fitModelList))));
        assertThat(content, containsString("\"id\":\""+id+"\""));
        assertThat(content, containsString("\"name\":\"Maria\""));
    }
}
