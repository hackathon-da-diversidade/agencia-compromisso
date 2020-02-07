package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.models.User;
import com.thoughtworks.agenciacompromisso.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void shouldReturnTrueIfUserIsListed() {
        User user = new User("user@example.com");
        when(userRepository.findByEmail("user@example.com")).thenReturn(user);
        Boolean isAuthorized = userService.isAuthorized(user.getEmail());
        assertThat(isAuthorized, is(true));
    }

    @Test
    public void shouldReturnFalseIfUserIsNotListed() {
        when(userRepository.findByEmail("not_authorized@example.com")).thenReturn(null);
        Boolean isAuthorized = userService.isAuthorized("not_authorized@example.com");
        assertThat(isAuthorized, is(false));
    }
}
