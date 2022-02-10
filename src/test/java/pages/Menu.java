package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import utils.SelenideActions;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

public class Menu {

    public static boolean hoverMenuItem(String menuItem) {
        SelenideActions
                .existVisibleScrollHover(
                        $(By.xpath(".//span[text()='" + menuItem + "']")));

        return $(By.xpath(".//span[text()='" + menuItem + "']")).is(Condition.visible);
    }

    public static boolean clickProductType(String product) {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(By.xpath(".//a[text()='" + product + "']")));

        String productUrl = product
                .toLowerCase(Locale.ROOT)
                .replaceAll(" & ", "+%26+")
                .replaceAll(" ", "+");

        return Selenide.webdriver().driver().url().contains(productUrl);
    }


}
