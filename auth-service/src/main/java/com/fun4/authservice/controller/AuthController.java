package com.fun4.authservice.controller;

import com.fun4.authservice.model.UserCredentials;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Api(value = "/auth", description = "auth service", produces = "application/json")
@RequestMapping("/auth")
public class AuthController {

    @PostMapping()
    public String login(@Valid @RequestBody UserCredentials userCredentials){
        return userCredentials.toString();
    }
}
