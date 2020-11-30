package pages.login;

import org.openqa.selenium.By;

public class Login_OR {

  public static By login = By.xpath("//button[@type='submit']");

  public static By passWord = By.xpath("//input[@name='password']");

  // username, password and login button
  public static By username = By.xpath("//input[@name='email']");
}
