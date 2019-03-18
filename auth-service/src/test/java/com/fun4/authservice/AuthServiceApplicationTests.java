package com.fun4.authservice;

import com.fun4.authservice.manager.AuthorizationManager;
import com.fun4.authservice.pojo.UserCredentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceApplicationTests {
	private AuthorizationManager authorizationManager;

	@Before
	public void setUp() throws Exception {
		this.authorizationManager = new AuthorizationManager();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testLogin(){
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUsername("test");
		userCredentials.setPassword("test");
		try {
			Assert.assertNotNull(this.authorizationManager.login(userCredentials));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testIfLoginFailed() {
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUsername("test");
		userCredentials.setPassword("asdasdasdasd");
		try {
			Assert.assertNotNull(this.authorizationManager.login(userCredentials));
		} catch (Exception e) {
		}
	}

	@Test
	public void testIfValidatesToken(){
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUsername("test");
		userCredentials.setPassword("test");
		String token = "";
		try {
			token = this.authorizationManager.login(userCredentials);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		try {
			Assert.assertTrue(this.authorizationManager.tokenValid(token));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testIfTestThrowsErrorIfTokenIsNotValid() {
		try {
			Assert.assertTrue(!this.authorizationManager.tokenValid("aaaa"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
