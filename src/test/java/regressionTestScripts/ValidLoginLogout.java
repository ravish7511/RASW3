package regressionTestScripts;

import java.util.List;

import generic.Excel;
import generic.Generic_Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pom.EnterTimeTrack;
import pom.Login_Page;

public class ValidLoginLogout extends Generic_Test {

	@Test
	public void validloginlogout() {
		String username = Excel.readData("regression", 1, 1);
		String password = Excel.readData("regression", 1, 2);
		String eTitle = Excel.readData("regression", 1, 3);
		
		test=reports.createTest("ValidLoginLogout", "user logins with valid credentials");

		Login_Page lp = new Login_Page(driver);
		test.info("test started");
		lp.setUsername(username);
		test.pass("user entered username");

		lp.setPassword(password);
		test.pass("user entered password");

		lp.clickLogin();
		test.pass("user clicked on login");

		EnterTimeTrack ep = new EnterTimeTrack(driver);
		ep.verifyTitle(5, "Enter");
		test.pass("home page title verified");
		Assert.assertEquals(driver.getTitle(), eTitle);

		ep.clickLogout();
		test.pass("user clicked on logout");

		lp.verifyTitle(5, "Login");
		test.pass("Login page verified");
		test.info("test ended");

	}

}
