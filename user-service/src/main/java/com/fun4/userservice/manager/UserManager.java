package com.fun4.userservice.manager;

import com.fun4.userservice.model.User;
import com.fun4.userservice.repository.UserRepository;

public class UserManager {
    private UserRepository userRepository;

    public UserManager() {
        this.userRepository = new UserRepository();
    }

    public User getUserByUsername(String username){
        return this.userRepository.getUserByUsername(username);
    }
}
