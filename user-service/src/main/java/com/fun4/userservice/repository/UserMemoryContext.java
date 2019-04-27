package com.fun4.userservice.repository;

import com.fun4.userservice.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserMemoryContext implements IUserRepository {
    private List<User> users = new ArrayList<>();

    public UserMemoryContext() {
        users.add(new User(100, "test@test.nl", "test", "tester", "test", "test"));
    }

    @Override
    public User getUserByUsername(String username) {
        return users.stream().filter(x -> x.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User getUserById(Integer userId) {
        return users.stream().filter(x -> x.getId() == userId).findFirst().orElse(null);
    }

    @Override
    public User addUser(User user) {
        Random random = new Random();
        user.setId(random.nextInt(1000));
        users.add(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }
}
