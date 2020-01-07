package com.thoughtworks.agenciacompromisso.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.SocialInformation;
import com.thoughtworks.agenciacompromisso.models.enums.*;
import com.thoughtworks.agenciacompromisso.services.FitModelService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(FitModelProfileController.class)
public class FitModelProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FitModelService fitModelService;

    private FitModel fitModel;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        getValidFitModel();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void shouldReturnStatusCode200AndFitModelInformationWhenGetFitModelWithId() throws Exception {
        fitModel.setId(new ObjectId().toString());

        when(fitModelService.get(any())).thenReturn(fitModel);

        MvcResult result = mockMvc.perform(
                get("/fit-model/"+fitModel.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content, is(objectMapper.writeValueAsString(fitModel)) );
    }

    public FitModel getValidFitModel(){
        fitModel = new FitModel();
        fitModel.setName("Maria dos Santos");
        fitModel.setPhoneNumber("51999111111");
        fitModel.setGenderExpression(GenderExpression.FEMALE);
        fitModel.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0));
        fitModel.setBirthday(LocalDate.parse("2008-12-10"));
        fitModel.setAddress("Avenida Ipiranga, 1963, Porto Alegre");
        fitModel.setAvailability(Availability.AFTERNOON);
        fitModel.setEducation(Education.INCOMPLETE_HIGH_SCHOOL);
        fitModel.setGuardianName("Claudia dos Santos");
        fitModel.setGuardianPhoneNumber("51999111111");
        fitModel.setIdentifyAsLGBTQIA(true);
        fitModel.setProjects("Nome do Projeto");
        SocialInformation socialInformation = new SocialInformation();
        socialInformation.setEthnicity(Ethnicity.PARDO);
        socialInformation.setFamilyIncome(FamilyIncome.TWO_MINIMUM_WAGE);
        socialInformation.setHousing(Housing.RENTED);
        socialInformation.setNumberOfChildren(0);
        socialInformation.setNumberOfResidents(3);
        socialInformation.setOccupation("Ocupacao");
        socialInformation.setOccupationMode(OccupationMode.AUTONOMOUS);
        fitModel.setSocialInformation(socialInformation);
        return fitModel;
    }
}
