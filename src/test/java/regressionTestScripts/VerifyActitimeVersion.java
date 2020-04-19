package regressionTestScripts;

import java.util.List;


import org.testng.annotations.Test;

import pom.EnterTimeTrack;
import pom.Login_Page;
import generic.Excel;
import generic.Generic_Test;

public class VerifyActitimeVersion extends Generic_Test {

	@Test
	public void verifyVersion() {
		String username = Excel.readData("regression", 3, 1);
		String password = Excel.readData("regression", 3, 2);
		String eTitle = Excel.readData("regression", 3, 3);
		test=reports.createTest("VerifyActitimeVersion", "user verifies actitimeversion");
		Login_Page lp = new Login_Page(driver);

		lp.setUsername(username);
		test.pass("user entered username");

		lp.setPassword(password);
		test.pass("user entered password");

		lp.clickLogin();
		test.pass("user clicked on login");

		EnterTimeTrack ep = new EnterTimeTrack(driver);
		ep.clickHelp();
		test.pass("user clicked on help");
		ep.clickActitime();
		test.pass("user clicked on abtActitime");

		ep.clickClose();
		test.pass("user clicked on close");

		ep.clickLogout();
		test.pass("user clicked on logout");
		test.info("test ended");

	}
}
