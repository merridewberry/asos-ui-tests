package steps;

import io.cucumber.java.en.And;
import org.testng.Assert;

public class Menu {

    @And("^I hover over (.+) button in the menu$")
    public static void hoverMenuItem(String button) {
        Assert.assertTrue(
                pages.Menu.hoverMenuItem(button));
    }

    @And("^I click on (.+) product type in the dropdown menu$")
    public static void clickProductType(String product) {
        Assert.assertTrue(
                pages.Menu.clickProductType(product));
    }

}
