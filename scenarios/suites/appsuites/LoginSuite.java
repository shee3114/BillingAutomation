package suites.appsuites;

import org.testng.annotations.Test;

import pages.CommonFunctions;
import pages.login.Login;
import pages.login.dashboard.Dashboard;
import suites.basesuite.ERPBaseSuite;

public class LoginSuite extends ERPBaseSuite {

  static int loggedInPatient = 0;

  CommonFunctions commonFunctions;

  Dashboard dashboard;

  Login login;

  /**
   * @Objective- Check Validation for Invalid User Name and Password
   * 
   * @param username- UserName
   * 
   * @param password- Password
   * 
   * @param expectedMessage- Expected Alert Message after entering invalid credentials
   */
  @Test(priority = 0, dataProvider = "multipleInput", enabled = false)
  public void checkValidationLoginPage(String username, String password, String expectedMessage) {

    // Create the Objects
    login = createObject("Login");
    commonFunctions = createObject("CommonFunctions");
    // Logout the patient if it is already logged in
    commonFunctions.logOut();
    // login to application with invalid credentials
    dashboard = login.login(username, password);
    // Verify alert check text on the login page.
    login.alertVerification(expectedMessage);
  }
  // @Test(priority = 1, enabled = true)
  // public void checkLogin() {
  // login = createObject("Login");
  // }
}
