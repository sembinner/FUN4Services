package com.example.userservice.controller;

import com.example.userservice.manager.UserManager;
import com.example.userservice.model.User;
import io.swagger.annotations.Api;
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

    @GetMapping("/getByUsername/{username}")
    public User getUserByUsername(@PathVariable(value = "username") String username){
        return userManager.getUserByUsername(username);
    }
}
