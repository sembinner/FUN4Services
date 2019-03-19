package com.fun4.userservice;

import com.fun4.userservice.manager.UserManager;
import com.fun4.userservice.model.User;
import com.fun4.userservice.repository.UserMemoryContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {
    private static UserManager manager;

    @BeforeClass
    public static void setUp() throws Exception {
        manager = new UserManager(new UserMemoryContext());
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetUser() {
        User user = this.manager.getUserByUsername("test");
        Assert.assertNotNull(user);
    }

    @Test
    public void testIfThrowsErrorIfUserDoesNotExist() {
        User user = this.manager.getUserByUsername("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Assert.assertNull(user);
    }

    @Test
    public void testAddUser() {
        try {
            this.manager.addUser(new User("user@user.nl", "newuser", "firstnameUser", "lastnameUser", "passwordUser"), "passwordUser");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        User user = manager.getUserByUsername("newuser");
        Assert.assertNotNull(user);
    }

    @Test
    public void testAddUserNameToLowerCase() {
        try {
            this.manager.addUser(new User("user@user.nl", "userTWO", "firstnameUser", "lastnameUser", "passwordUser"), "passwordUser");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        User user = manager.getUserByUsername("usertwo");
        Assert.assertNotNull(user);
    }
    @Test
    public void testIfThrowsErrorIfUserNameAlreadyExists() {
        try {
            this.manager.addUser(new User("user@user.nl", "duplicateUser", "firstnameUser", "lastnameUser", "passwordUser"), "passwordUser");
            this.manager.addUser(new User("user@user.nl", "duplicateUser", "firstnameUser", "lastnameUser", "passwordUser"), "passwordUser");
        } catch (Exception e) {
            return;
        }
        Assert.fail();
    }
}
