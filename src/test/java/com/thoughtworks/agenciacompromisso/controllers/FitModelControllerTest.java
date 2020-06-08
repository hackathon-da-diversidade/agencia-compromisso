package com.thoughtworks.agenciacompromisso.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thoughtworks.agenciacompromisso.exceptions.CandidateNotFoundException;
import com.thoughtworks.agenciacompromisso.models.FitModel;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.SocialInformation;
import com.thoughtworks.agenciacompromisso.models.enums.*;
import com.thoughtworks.agenciacompromisso.services.FitModelService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FitModelController.class)
public class FitModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FitModelService fitModelService;

    private FitModel fitModel;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        populateValidFitModel();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void shouldReturnStatusCode201AndLocationHeaderWhenPostFitModel() throws Exception {
        FitModel fitModelReturned = new FitModel();
        fitModelReturned.setId(new ObjectId().toString());

        when(fitModelService.create(ArgumentMatchers.any())).thenReturn(fitModelReturned);

        mockMvc.perform(
                post("/fit-model")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fitModel)))
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
                get("/fit-model")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content, not(is(new ObjectMapper().writeValueAsString(fitModelList))));
        assertThat(content, containsString("\"id\":\"" + id + "\""));
        assertThat(content, containsString("\"name\":\"Maria dos Santos\""));
        assertThat(content, not(containsString("\"phoneNumber\":\"51999111111\"")));
    }

    @Test
    public void shouldReturnStatusCode200AndFitModelInformationWhenGetFitModelWithId() throws Exception {
        fitModel.setId(new ObjectId().toString());

        when(fitModelService.get(any())).thenReturn(fitModel);

        MvcResult result = mockMvc.perform(
                get("/fit-model/" + fitModel.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content, is(objectMapper.writeValueAsString(fitModel)));
    }

    @Test
    public void shouldUpdateFitModel() throws Exception {
        ObjectId id = new ObjectId();
        fitModel.setId(id.toString());

        FitModel updatedFitModel = new FitModel();

        BeanUtils.copyProperties(fitModel, updatedFitModel);
        updatedFitModel.setName("Ana Carolina");
        updatedFitModel.setPhoneNumber("58993249582");

        when(fitModelService.update(any(), any())).thenReturn(updatedFitModel);
        when(fitModelService.get(any())).thenReturn(fitModel);

        MvcResult result = mockMvc.perform(
                put("/fit-model/" + fitModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFitModel)))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content, containsString("\"id\":\"" + fitModel.getId() + "\""));
        assertThat(content, containsString("\"name\":\"Ana Carolina\""));
        assertThat(content, containsString("\"phoneNumber\":\"58993249582\""));

        verify(fitModelService, times(1)).get(any());
        verify(fitModelService, times(1)).update(any(), any());
        verifyNoMoreInteractions(fitModelService);
    }

    @Test
    public void searchFitModelByName() throws Exception {
        FitModel fitModel = new FitModel();
        fitModel.setId("1");
        fitModel.setName("Name");

        Page<FitModel> fitModelPage = new PageImpl<>(Collections.singletonList(fitModel));

        when(fitModelService.search(any(), any())).thenReturn(fitModelPage);

        mockMvc.perform(
                get("/fit-model/search").param("name", fitModel.getName())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(fitModel.getId())))
                .andExpect(jsonPath("$.content[0].name", is(fitModel.getName())));

        verify(fitModelService).search(any(), any());
    }

    @Test
    public void shouldReturnFitModelPageWithList() throws Exception {
        Page<FitModel> page = new PageImpl<>(Collections.singletonList(fitModel));
        when(fitModelService.findAllPage(any())).thenReturn(page);

        mockMvc.perform(
                get("/fit-model/paginated").param("page", "1").param("size", "5")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));

        verify(fitModelService).findAllPage(any());
    }

    public void populateValidFitModel() {
        SocialInformation socialInformation = new SocialInformation();
        socialInformation.setEthnicity(Ethnicity.PARDO);
        socialInformation.setFamilyIncome(FamilyIncome.TWO_MINIMUM_WAGE);
        socialInformation.setHousing(Housing.RENTED);
        socialInformation.setNumberOfChildren(0);
        socialInformation.setNumberOfResidents(3);
        socialInformation.setOccupation("Ocupacao");
        socialInformation.setOccupationMode(OccupationMode.AUTONOMOUS);

        fitModel = new FitModel();
        fitModel.setName("Maria dos Santos");
        fitModel.setPhoneNumber("(51)999111111");
        fitModel.setGenderExpression(GenderExpression.FEMALE);
        fitModel.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0, "M", 42, 40));
        fitModel.setBirthday(LocalDate.parse("2008-12-10"));
        fitModel.setAddress("Avenida Ipiranga, 1963, Porto Alegre");
        fitModel.setAvailability(Availability.AFTERNOON);
        fitModel.setEducation(Education.INCOMPLETE_HIGH_SCHOOL);
        fitModel.setGuardianName("Claudia dos Santos");
        fitModel.setGuardianPhoneNumber("(51)999111111");
        fitModel.setIdentifyAsLGBTQIA(true);
        fitModel.setProjects("Nome do Projeto");
        fitModel.setSocialInformation(socialInformation);
    }

    @Test
    public void shouldDeleteCandidate() throws Exception {
        String id = "id";

        doNothing().when(fitModelService).delete(id);

        MockHttpServletRequestBuilder request = delete(String.format("/fit-model/%s", id));

        this.mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(fitModelService).delete(id);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToDeleteCandidateThatDoesNotExist() throws Exception {
        String id = "id";

        doThrow(CandidateNotFoundException.class).when(fitModelService).delete(id);

        MockHttpServletRequestBuilder request = delete(String.format("/fit-model/%s", id));

        this.mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(fitModelService).delete(id);
    }
}

