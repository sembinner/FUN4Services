package com.fun4.authservice.controller;

import com.fun4.authservice.pojo.UserCredentials;
import com.fun4.authservice.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Api(value = "/auth", description = "auth service", produces = "application/json")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    public String login(@Valid @RequestBody UserCredentials userCredentials){
        return jwtTokenProvider.createToken(userCredentials.getUsername(), "user");
    }
}
