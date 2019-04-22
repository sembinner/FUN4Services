package com.fun4.userservice.manager;

import com.fun4.userservice.model.User;
import com.fun4.userservice.repository.IUserRepository;
import com.fun4.userservice.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {
    private IUserRepository userRepository;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public UserManager(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username){
        return this.userRepository.getUserByUsername(username);
    }

    public User getUserById(int id) {return  this.userRepository.getUserById(id);}

    public User addUser(User user, String confirmPassword) throws Exception{
        //Set username and email to lowercase
        user.setUsername(user.getUsername().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());

        this.userChecks(user, confirmPassword);

        //Hash password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        return this.userRepository.addUser(user);
    }

    public User editUser(User user, String confirmPassword) throws Exception{
        //Set username and email to lowercase
        user.setUsername(user.getUsername().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());

        this.userChecks(user, confirmPassword);

        return this.userRepository.editUser(user);
    }

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    private void userChecks(User user, String confirmPassword) throws Exception{
        //Check if already user with username
        if(this.getUserByUsername(user.getUsername())!= null){
            throw new Exception(MessageFormat.format("There is already a user registered with the username {0}", user.getUsername()));
        }
        if(confirmPassword != null) {
            if (!user.getPassword().equals(confirmPassword)) {
                throw new Exception("The passwords do not match!");
            }
        }

        //Check if email is valid
        if(!validate(user.getEmail())){
            throw new Exception(MessageFormat.format("\"{0}\" is not a correct email format!", user.getEmail()));
        }
    }
}
