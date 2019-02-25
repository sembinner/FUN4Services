package com.fun4.userservice.controller;

import com.fun4.userservice.manager.UserManager;
import com.fun4.userservice.model.User;
import com.fun4.userservice.viewmodel.CreateUserViewmodel;
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

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable(value = "username") String username){
        return userManager.getUserByUsername(username);
    }

    @PostMapping("/add")
    public User addUser(CreateUserViewmodel viewmodel){
        User user  = new User(viewmodel.getEmail(), viewmodel.getUsername(), viewmodel.getFirstName(), viewmodel.getLastName(), viewmodel.getPassword());
        return this.userManager.addUser(user);
    }



}
