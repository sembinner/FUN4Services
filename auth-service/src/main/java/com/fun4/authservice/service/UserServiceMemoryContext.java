package com.fun4.authservice.service;

import com.fun4.authservice.pojo.UserCredentials;

import java.util.ArrayList;
import java.util.List;

public class UserServiceMemoryContext implements IUserService {
    private List<UserCredentials> userCredentials = new ArrayList<>();

    public UserServiceMemoryContext() {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setId(100);
        userCredentials.setUsername("test");
        userCredentials.setPassword("$2a$10$pJk3WZxbp7MLMBAMld4R8uO18VoxA6MoALIHQvR6UQ64fuQ8SsgEy");
        this.userCredentials.add(userCredentials);
    }

    @Override
    public UserCredentials getUserCredentialsByUsername(String username) throws Exception {
        return userCredentials.stream().filter(x -> x.getUsername().equals(username)).findFirst().orElse(null);
    }
}
