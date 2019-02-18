package com.example.userservice.controller;

import com.example.userservice.exception.ResourceNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepositoy;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Api(value="/users",description="User Service",produces ="application/json")
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepositoy userRepositoy;

    // Get all Users
    @GetMapping()
    public List<User> getAllUsers(){
        return userRepositoy.findAll();
    }

    // Create new User
    @PostMapping()
    public User createUser(@Valid @RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepositoy.save(user);
    }

    // Get a single User - By Id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") String userId){
        return userRepositoy.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    // Update User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") String userId,
                           @Valid @RequestBody User userDetails) {
        User user = userRepositoy.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPassword(userDetails.getPassword());

        User updatedUser = userRepositoy.save(user);
        return updatedUser;
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") String userId) {
        User user = userRepositoy.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepositoy.delete(user);

        return ResponseEntity.ok().build();
    }
}
