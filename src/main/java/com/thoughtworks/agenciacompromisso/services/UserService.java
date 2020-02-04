package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.models.User;
import com.thoughtworks.agenciacompromisso.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Boolean isAuthorized(String email) {

        List<User> authorized = userRepository.findAll();

        Boolean isAuthorized = false;

        for (User user : authorized)  {
            if (email.equals(user.getEmail())) {
                isAuthorized = true;
            }
        }

        return isAuthorized;
    }
}
