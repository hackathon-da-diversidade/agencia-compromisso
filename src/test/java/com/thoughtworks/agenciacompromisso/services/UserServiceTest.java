package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.models.User;
import com.thoughtworks.agenciacompromisso.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
        List<User> userList = new ArrayList<>();
        User user = new User("user@example.com");

        userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);

        Boolean isAuthorized = userService.isAuthorized(user.getEmail());

        assertThat(isAuthorized, is(true));
    }

    @Test
    public void shouldReturnFalseIfUserIsNotListed() {
        List<User> userList = new ArrayList<>();
        User user = new User("user@example.com");

        userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);

        Boolean isAuthorized = userService.isAuthorized("not_authorized@example.com");

        assertThat(isAuthorized, is(false));
    }
}
