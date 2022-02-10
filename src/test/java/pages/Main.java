package pages;

import com.codeborne.selenide.Selenide;
import utils.SelenideActions;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Main {

    public static boolean clickDepartment(String department) {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(byText(department)));
        if (department.contains("WOMEN")) {
            return Selenide.webdriver().driver().url().contains("https://www.asos.com/women");
        } else {
            return Selenide.webdriver().driver().url().contains("https://www.asos.com/men");
        }
    }

    public static void acceptCookies() {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(byText("That's ok")));
    }
}
