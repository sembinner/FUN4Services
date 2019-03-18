package com.fun4.userservice;

import com.fun4.userservice.manager.UserManager;
import com.fun4.userservice.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {
    private UserManager manager;

    @Before
    public void setUp() throws Exception {
        this.manager = new UserManager();
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
}
