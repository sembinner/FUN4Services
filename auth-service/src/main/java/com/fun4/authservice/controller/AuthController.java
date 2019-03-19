package com.fun4.authservice.controller;

import com.fun4.authservice.manager.AuthorizationManager;
import com.fun4.authservice.pojo.UserCredentials;
import com.fun4.authservice.pojo.TokenPojo;
import com.fun4.authservice.pojo.ValidateTokenPojo;
import com.fun4.authservice.service.UserService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Api(value = "/auth", description = "auth service", produces = "application/json")
@RequestMapping("/auth")
public class AuthController {
    private AuthorizationManager authorizationManager;

    public AuthController() {
        this.authorizationManager = new AuthorizationManager(new UserService());
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserCredentials userCredentials){
        try {
            String s = this.authorizationManager.login(userCredentials);
            return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(new TokenPojo(s)));
        } catch (Exception e) {
          return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/tokenValid/{token}")
    public ResponseEntity tokenValid(@PathVariable(value = "token")String token){
        //Only use this when website starts to check if user should still be logged in. For other features check in the service where the feature is made.
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ValidateTokenPojo(this.authorizationManager.tokenValid(token)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
