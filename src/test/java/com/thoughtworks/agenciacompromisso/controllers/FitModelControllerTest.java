package com.thoughtworks.agenciacompromisso.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FitModelController.class)

public class FitModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn201WhenPostingFitModel() throws Exception {
        mockMvc.perform(post("/fit-model"))
        .andExpect(status().isCreated());
    }

}