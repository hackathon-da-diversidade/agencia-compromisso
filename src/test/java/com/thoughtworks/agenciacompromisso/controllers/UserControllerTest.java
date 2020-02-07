package com.thoughtworks.agenciacompromisso.controllers;

import com.thoughtworks.agenciacompromisso.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserService user;

    @Test
    public void shouldReturnStatusCode200AndLocationHeaderIfUserIsAuthorized() throws Exception {

        when(userService.isAuthorized(ArgumentMatchers.any())).thenReturn(true);

        mockMvc.perform(
                get("/authorize/"+"example@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", containsString("/menu")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusCode404WhenUserIsNotAuthorized() throws Exception {

        when(userService.isAuthorized(ArgumentMatchers.any())).thenReturn(false);

        mockMvc.perform(
                get("/authorize/"+"example@example.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
