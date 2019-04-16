package com.fun4.userservice.controller;

import com.fun4.userservice.manager.UserManager;
import com.fun4.userservice.model.User;
import com.fun4.userservice.repository.UserRepository;
import com.fun4.userservice.security.JwtTokenProvider;
import com.fun4.userservice.viewmodel.CreateUserViewmodel;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@Api(value="/users",description="User Service",produces ="application/json")
@RequestMapping("/users")
public class UserController {
    private UserManager userManager;
    private JwtTokenProvider jwtTokenProvider;

    public UserController() {
        this.userManager = new UserManager(new UserRepository());
        this.jwtTokenProvider = new JwtTokenProvider();
    }

    @GetMapping("/{username}")
    public ResponseEntity getUserByUsername(@PathVariable(value = "username") String username){
        return ResponseEntity.status(HttpStatus.OK).body(userManager.getUserByUsername(username));
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable(value = "id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(userManager.getUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody CreateUserViewmodel viewmodel){
        User user  = new User(viewmodel.getEmail(), viewmodel.getUsername(), viewmodel.getFirstName(), viewmodel.getLastName(), viewmodel.getPassword());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.userManager.addUser(user, viewmodel.getConfirmPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/validateToken/{token}")
    public ResponseEntity validateToken(@PathVariable(value = "token") String token){
        //This is just to test if JWT is working!
        try {
            if(!jwtTokenProvider.validateToken(token)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token not valid!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        Pattern pattern = Pattern.compile("(?<=Id=)(.*)(?=})");
        Matcher matcher = pattern.matcher(jwtTokenProvider.getBody(token));
        if (matcher.find()) {
            return ResponseEntity.status(HttpStatus.OK).body(matcher.group());
        }

        return ResponseEntity.status(HttpStatus.OK).body(jwtTokenProvider.getBody(token));
    }



}
