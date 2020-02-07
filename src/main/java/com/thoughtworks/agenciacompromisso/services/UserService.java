package com.thoughtworks.agenciacompromisso.services;

import com.thoughtworks.agenciacompromisso.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Boolean isAuthorized(String email) {

        Boolean isAuthorized = false;

        if (userRepository.findByEmail(email) != null) {
            isAuthorized = true;
        }

        return isAuthorized;
    }
}
