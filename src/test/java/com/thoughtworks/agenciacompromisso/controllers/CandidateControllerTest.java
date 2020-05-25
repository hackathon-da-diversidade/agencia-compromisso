package com.thoughtworks.agenciacompromisso.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thoughtworks.agenciacompromisso.models.Candidate;
import com.thoughtworks.agenciacompromisso.exceptions.CandidateNotFoundException;
import com.thoughtworks.agenciacompromisso.models.Sizes;
import com.thoughtworks.agenciacompromisso.models.SocialInformation;
import com.thoughtworks.agenciacompromisso.models.enums.*;
import com.thoughtworks.agenciacompromisso.services.CandidateService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
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

@WebMvcTest(CandidateController.class)
public class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;

    private Candidate candidate;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        populateValidCandidate();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @AfterEach
    public void tearDown() {
        reset(candidateService);
    }

    @Test
    public void shouldReturnStatusCode201AndLocationHeaderWhenPostCandidate() throws Exception {
        Candidate candidateReturned = new Candidate();
        candidateReturned.setId(new ObjectId().toString());

        when(candidateService.create(ArgumentMatchers.any())).thenReturn(candidateReturned);

        mockMvc.perform(
                post("/candidate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidate)))
                .andExpect(header().string("location", containsString("candidate/" + candidateReturned.getId())))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnStatusCode200AndCandidateListWhenGetCandidate() throws Exception {
        List<Candidate> candidateList = new ArrayList<>();
        String id = new ObjectId().toString();
        candidate.setId(id);
        candidateList.add(candidate);
        candidateList.add(candidate);

        when(candidateService.getAll()).thenReturn(candidateList);

        MvcResult result = mockMvc.perform(
                get("/candidate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content, not(is(new ObjectMapper().writeValueAsString(candidateList))));
        assertThat(content, containsString("\"id\":\"" + id + "\""));
        assertThat(content, containsString("\"name\":\"Maria dos Santos\""));
        assertThat(content, not(containsString("\"phoneNumber\":\"51999111111\"")));
    }

    @Test
    public void shouldReturnStatusCode200AndCandidateInformationWhenGetCandidateWithId() throws Exception {
        candidate.setId(new ObjectId().toString());

        when(candidateService.get(any())).thenReturn(candidate);

        MvcResult result = mockMvc.perform(
                get("/candidate/" + candidate.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content, is(objectMapper.writeValueAsString(candidate)));
    }

    @Test
    public void shouldUpdateCandidate() throws Exception {
        ObjectId id = new ObjectId();
        candidate.setId(id.toString());

        Candidate updatedCandidate = new Candidate();

        BeanUtils.copyProperties(candidate, updatedCandidate);
        updatedCandidate.setName("Ana Carolina");
        updatedCandidate.setPhoneNumber("58993249582");

        when(candidateService.update(any(), any())).thenReturn(updatedCandidate);
        when(candidateService.get(any())).thenReturn(candidate);

        MvcResult result = mockMvc.perform(
                put("/candidate/" + candidate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCandidate)))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content, containsString("\"id\":\"" + candidate.getId() + "\""));
        assertThat(content, containsString("\"name\":\"Ana Carolina\""));
        assertThat(content, containsString("\"phoneNumber\":\"58993249582\""));

        verify(candidateService, times(1)).get(any());
        verify(candidateService, times(1)).update(any(), any());
        verifyNoMoreInteractions(candidateService);
    }

    @Test
    public void searchCandidateByName() throws Exception {
        Candidate candidate = new Candidate();
        candidate.setId("1");
        candidate.setName("Name");

        Page<Candidate> candidatePage = new PageImpl<>(Collections.singletonList(candidate));

        when(candidateService.search(any(), any())).thenReturn(candidatePage);

        mockMvc.perform(
                get("/candidate/search").param("name", candidate.getName())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(candidate.getId())))
                .andExpect(jsonPath("$.content[0].name", is(candidate.getName())));

        verify(candidateService).search(any(), any());
    }

    @Test
    public void shouldReturnCandidatePageWithList() throws Exception {
        Page<Candidate> page = new PageImpl<>(Collections.singletonList(candidate));
        when(candidateService.findAllPage(any())).thenReturn(page);

        mockMvc.perform(
                get("/candidate/paginated").param("page", "1").param("size", "5")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));

        verify(candidateService).findAllPage(any());
    }

    public void populateValidCandidate() {
        SocialInformation socialInformation = new SocialInformation();
        socialInformation.setEthnicity(Ethnicity.PARDO);
        socialInformation.setFamilyIncome(FamilyIncome.TWO_MINIMUM_WAGE);
        socialInformation.setHousing(Housing.RENTED);
        socialInformation.setNumberOfChildren(0);
        socialInformation.setNumberOfResidents(3);
        socialInformation.setOccupation("Ocupacao");
        socialInformation.setOccupationMode(OccupationMode.AUTONOMOUS);

        candidate = new Candidate();
        candidate.setName("Maria dos Santos");
        candidate.setPhoneNumber("(51)999111111");
        candidate.setGenderExpression(GenderExpression.FEMALE);
        candidate.setSizes(new Sizes(108.0, 87.0, 100.0, 160.0, "M", 42, 40));
        candidate.setBirthday(LocalDate.parse("2008-12-10"));
        candidate.setAddress("Avenida Ipiranga, 1963, Porto Alegre");
        candidate.setAvailability(Availability.AFTERNOON);
        candidate.setEducation(Education.INCOMPLETE_HIGH_SCHOOL);
        candidate.setGuardianName("Claudia dos Santos");
        candidate.setGuardianPhoneNumber("(51)999111111");
        candidate.setIdentifyAsLGBTQIA(true);
        candidate.setProjects("Nome do Projeto");
        candidate.setSocialInformation(socialInformation);
    }

    @Test
    public void shouldDeleteCandidate() throws Exception {
        String id = "id";

        doNothing().when(candidateService).delete(id);

        MockHttpServletRequestBuilder request = delete(String.format("/candidate/%s", id));

        this.mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(candidateService).delete(id);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToDeleteCandidateThatDoesNotExist() throws Exception {
        String id = "id";

        doThrow(CandidateNotFoundException.class).when(candidateService).delete(id);

        MockHttpServletRequestBuilder request = delete(String.format("/candidate/%s", id));

        this.mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(candidateService).delete(id);
    }
}

