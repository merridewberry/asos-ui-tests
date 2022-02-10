package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class SelenideActions {

    public static void existVisibleScrollHoverClick(SelenideElement element) {
        element
                .shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .scrollTo()
                .hover()
                .click();
    }

    public static void existVisibleScrollHover(SelenideElement element) {
        element
                .shouldBe(Condition.exist)
                .shouldBe(Condition.visible)
                .scrollTo()
                .hover();
    }

}
