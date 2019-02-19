package com.fun4.authservice.service;

import com.fun4.authservice.pojo.UserCredentials;

public interface IUserService {
    UserCredentials getUserCredentialsByUsername(String username) throws Exception;
}
