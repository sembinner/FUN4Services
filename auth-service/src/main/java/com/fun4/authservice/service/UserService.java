package com.fun4.authservice.service;

import com.fun4.authservice.pojo.UserCredentials;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class UserService implements IUserService {

    String url = "http://user-service:9001/users";

    public UserCredentials getUserCredentialsByUsername(String username) throws Exception {
       HttpResponse jsonResponse =  Unirest.get(url+"/{username}")
                .routeParam("username", username)
               .asJson();
       return new Gson().fromJson(jsonResponse.getBody().toString(), UserCredentials.class);
    }
}
