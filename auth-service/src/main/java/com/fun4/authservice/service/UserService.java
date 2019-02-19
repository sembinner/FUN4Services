package com.fun4.authservice.service;

import com.fun4.authservice.pojo.UserCredentials;
import com.mashape.unirest.http.Unirest;

public class UserService implements IUserService {

    String url = "http://localhost:9001/users";

    public UserCredentials getUserCredentialsByUsername(String username) throws Exception {
        Unirest.get(url+"/")
    }
}
