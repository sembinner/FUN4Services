package com.fun4.userservice.repository;

import com.fun4.userservice.model.User;

public interface IUserRepository {
    public User getUserByUsername(String username);
    public User addUser(User user);
    public User editUser(User user);

    User getUserById(int id);
}
