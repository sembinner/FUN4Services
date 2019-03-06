package com.fun4.authservice.controller;

import com.fun4.authservice.manager.AuthorizationManager;
import com.fun4.authservice.pojo.UserCredentials;
import com.fun4.authservice.pojo.tokenPojo;
import com.fun4.authservice.security.JwtTokenProvider;
import com.fun4.authservice.service.IUserService;
import com.fun4.authservice.service.UserService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
        this.authorizationManager = new AuthorizationManager();
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserCredentials userCredentials){
        try {
            String s = this.authorizationManager.login(userCredentials);
            return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(new tokenPojo(s)));
        } catch (Exception e) {
          return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
