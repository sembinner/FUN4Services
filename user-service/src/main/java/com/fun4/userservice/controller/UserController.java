package com.fun4.userservice.controller;

import com.fun4.userservice.manager.UserManager;
import com.fun4.userservice.model.User;
import com.fun4.userservice.viewmodel.CreateUserViewmodel;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(value="/users",description="User Service",produces ="application/json")
@RequestMapping("/users")
public class UserController {
    UserManager userManager;

    public UserController() {
        this.userManager = new UserManager();
    }

    @GetMapping("/{username}")
    public ResponseEntity getUserByUsername(@PathVariable(value = "username") String username){
        return ResponseEntity.status(HttpStatus.OK).body(userManager.getUserByUsername(username));
    }

    @PostMapping("/add")
    public ResponseEntity addUser(CreateUserViewmodel viewmodel){
        //TODO check jwt
        User user  = new User(viewmodel.getEmail(), viewmodel.getUsername(), viewmodel.getFirstName(), viewmodel.getLastName(), viewmodel.getPassword());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.userManager.addUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
