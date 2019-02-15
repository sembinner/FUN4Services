package com.example.userservice.controller;

import com.example.userservice.exception.ResourceNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
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
        return userRepositoy.save(user);
    }

    // Get a single User - By Id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId){
        return userRepositoy.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", userId));
    }

    // Update User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody User userDetails) {
        User user = userRepositoy.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", userId));

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
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long userId) {
        User user = userRepositoy.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepositoy.delete(user);

        return ResponseEntity.ok().build();
    }
}
