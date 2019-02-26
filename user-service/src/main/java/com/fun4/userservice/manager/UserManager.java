package com.fun4.userservice.manager;

import com.fun4.userservice.model.User;
import com.fun4.userservice.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {
    private UserRepository userRepository;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public UserManager() {
        this.userRepository = new UserRepository();
    }

    public User getUserByUsername(String username){
        return this.userRepository.getUserByUsername(username);
    }

    public User addUser(User user) throws Exception{
        //Check if already user with username
        if(this.getUserByUsername(user.getUsername())!= null){
            throw new Exception(MessageFormat.format("There is already a user registered with the username {0}", user.getUsername()));
        }

        //Check if email is valid
        if(!validate(user.getEmail())){
            throw new Exception(MessageFormat.format("\"{0}\" is not a correct email format!", user.getEmail()));
        }

        //Hash password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        return this.userRepository.addUser(user);
    }

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
