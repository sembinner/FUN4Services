package com.example.userservice.manager;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;

public class UserManager {
    private UserRepository userRepository;

    public UserManager() {
        this.userRepository = new UserRepository();
    }

    public User getUserByUsername(String username){
        return this.userRepository.getUserByUsername(username);
    }
}
