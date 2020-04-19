package regressionTestScripts;

import generic.Excel;
import generic.Generic_Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pom.Login_Page;

public class InvalidLogin extends Generic_Test {

	@Test
	public void invalidLogin() {
		String username = Excel.readData("regression", 2, 1);
		String password = Excel.readData("regression", 2, 2);
		String eTitle = Excel.readData("regression", 2, 3);

		test=reports.createTest("InvalidLogin", "user tries to login with invalid credentials");
		Login_Page lp = new Login_Page(driver);
		test.info("test started");
		lp.setUsername(username);
		test.pass("user entered username");
		lp.setPassword(password);
		test.pass("user entered password");
		lp.clickLogin();
		test.pass("user clicked on login");
		lp.verifyErrmsg();
		test.pass("error message verified");
		lp.verifyTitle(10, "Login");
		test.pass("title verified");
		Assert.assertEquals(driver.getTitle(), eTitle);
		test.info("test ended");

	}

}
