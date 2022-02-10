package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Catalogue {

    @When("I click 'Sort' button")
    public static void clickSort() {
        Assert
                .assertTrue(
                        pages.Catalogue.clickSort());
    }

    @And("^I click (.+) in the sorting options dropdown menu$")
    public static void clickSortOption(String sort) {
        pages.Catalogue.selectSort(sort);
    }

    @Then("^All the articles are sorted in the following order: (.+)$")
    public static void assertSorting(String sort) {
        Assert
                .assertTrue(
                        pages.Catalogue.checkSortType(sort));
    }

    @And("I click 'Load more' button")
    public static void clickLoadMore() {
        Assert
                .assertTrue(
                        pages.Catalogue.loadMore());
    }

    @When("^I click (.+) filter button$")
    public static void clickFilter(String filter) {
        Assert
                .assertTrue(
                        pages.Catalogue.clickFilter(filter));
    }

    @And("^I select (.+) filter in the dropdown menu$")
    public static void selectFilter(String filter) {
        Assert
                .assertTrue(
                        pages.Catalogue.selectFilter(filter));
    }

    @Then("^All displayed articles are from (.+) brand$")
    public static void checkBrandFilter(String brand) {
        Assert
                .assertTrue(
                        pages.Catalogue.checkBrandFilter(brand));
    }

    @Then("^All displayed articles are from (.+) and (.+) brands$")
    public static void checkTwoBrandsFilter(String brand1, String brand2) {
        Assert
                .assertTrue(
                        pages.Catalogue.checkTwoBrandsFilter(brand1, brand2));
    }

    @Then("^All displayed articles are from (.+) category$")
    public static void checkSaleNSCategory(String category) {
        Assert
                .assertTrue(
                        pages.Catalogue.checkSaleNSFilter(category));
    }

    @And("^I move sliders so they cover (.+) part of price range$")
    public static void selectPriceRange(String range) {
        Assert
                .assertTrue(
                        pages.Catalogue.selectPriceRange(range));
    }

    @Then("All the displayed prices are within selected price range")
    public static void checkPriceRange() {
        Assert
                .assertTrue(
                        pages.Catalogue.checkPriceRange());
    }

}
