package com.fun4.authservice.manager;

import com.fun4.authservice.pojo.UserCredentials;
import com.fun4.authservice.security.JwtTokenProvider;
import com.fun4.authservice.service.IUserService;
import com.fun4.authservice.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

public class AuthorizationManager{

    private IUserService userService;

    public AuthorizationManager() {
    this.userService = new UserService();
    }

    public String login(UserCredentials userCredentials) throws Exception{
        UserCredentials _userCredentials = this.userService.getUserCredentialsByUsername(userCredentials.getUsername());
        //Check if user is found
        if(_userCredentials == null){
            throw new Exception("The given username and password combination does not match!");
        }
        //Check if password matches
        if(!BCrypt.checkpw(userCredentials.getPassword(), _userCredentials.getPassword())){
            throw new Exception("The given username and password combination does not match!");
        }

        return JwtTokenProvider.getInstance().createToken(userCredentials.getUsername(), "user");

    }
}
