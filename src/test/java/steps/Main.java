package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.testng.Assert;

public class Main {

    @Given("^I press (.+) button on the main page$")
    public static void clickDepartment(String department) {
        Assert.assertTrue(
                pages.Main.clickDepartment(department));
    }

    @And("I accept Cookies")
    public static void acceptCookies() {
        pages.Main.acceptCookies();
    }
}
