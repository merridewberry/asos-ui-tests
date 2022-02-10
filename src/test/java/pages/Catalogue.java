package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import utils.SelenideActions;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Catalogue {

    private static final Logger log = (Logger) LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static boolean clickSort() {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(byText("Sort")));
        return $(By.xpath(".//ul[@data-test-id='sortOptions']")).is(Condition.visible);
    }

    public static boolean selectSort(String sort) {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(byText(sort)));

        clickSort();
        return $(By.xpath(".//li[text()='Price low to high' and @aria-selected='true']")).exists();
    }

    public static boolean checkSortType(String sort) {
        if (sort.equals("Price low to high")) {
            return checkSort("asc");
        } else if (sort.equals("Price high to low")) {
            return checkSort("desc");
        } else {
            log.error("Invalid sorting option");
            return false;
        }
    }

    private static boolean checkSort(String sort) {
        List<SelenideElement> articles = $$(By.xpath(".//article"));
        List<Double> prices = getPrices(articles);

        if (sort.equals("asc")) {
            for (int i = 0; i < prices.size() - 1; i++) {
                if ((prices.get(i + 1) - prices.get(i)) < 0) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < prices.size() - 1; i++) {
                if ((prices.get(i) - prices.get(i + 1)) < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<Double> getPrices(List<SelenideElement> articles) {
        List<Double> prices = new ArrayList<>();

        for (SelenideElement article : articles) {
            if (article.findElements(By.xpath(".//a/p/span")).size() > 1) {
                String price = article
                        .findElement(By.xpath(".//a/p/span/span[@data-auto-id='productTileSaleAmount']"))
                        .getText()
                        .replaceAll("£", "");
                prices.add(Double.parseDouble(price));
            } else {
                String price = article
                        .findElement(By.xpath(".//a/p/span/span"))
                        .getText()
                        .replaceAll("£", "");
                prices.add(Double.parseDouble(price));
            }
        }
        return prices;
    }

    public static boolean loadMore() {
        List<SelenideElement> articles = $$(By.xpath(".//article"));
        String lastId = articles.get(articles.size() - 1).getAttribute("id");

        try {
            SelenideActions
                    .existVisibleScrollHoverClick(
                            $(byText("Load more")));

            $(byText("Load more")).shouldBe(Condition.visible);
        } catch (Exception e) {
            log.error("Nothing to load");
        }

        List<SelenideElement> articlesLoaded = $$(By.xpath(".//article"));
        String lastIdLoaded = articlesLoaded.get(articlesLoaded.size() - 1).getAttribute("id");

        return !lastId.equals(lastIdLoaded);
    }

    public static boolean clickFilter(String filter) {
        SelenideActions
                .existVisibleScrollHoverClick(
                        $(byText(filter)));

        return $(By.xpath(".//div[@data-filter-dropdown='true']/div")).exists();
    }

    public static boolean selectFilter(String filter) {
        $(By.xpath(".//li/div/label/div[text()='" + filter + "']")).click();

        $(By.xpath(".//p[@data-auto-id='selectedFacetValueList' and contains(text(), '" + filter + "')]")).shouldBe(Condition.visible);

        return $(By.xpath(".//p[@data-auto-id='selectedFacetValueList']")).getText().contains(filter);
    }

    public static boolean checkBrandFilter(String brand) {
        List<SelenideElement> articleDescriptions = $$(By.xpath(".//div[@data-auto-id='productTileDescription']/div/div/h2"));
        for (SelenideElement description : articleDescriptions) {
            if (!description.getText().toLowerCase().contains(brand.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkTwoBrandsFilter(String brand1, String brand2) {
        List<SelenideElement> articleDescriptions = $$(By.xpath(".//div[@data-auto-id='productTileDescription']/div/div/h2"));
        List<SelenideElement> brand1List = new ArrayList<>();
        List<SelenideElement> brand2List = new ArrayList<>();

        for (SelenideElement description : articleDescriptions) {
            String lowCaseDescription = description.getText().toLowerCase();
            if (!lowCaseDescription.contains(brand1.toLowerCase()) && !lowCaseDescription.contains(brand2.toLowerCase())) {
                return false;
            }
            if (description.getText().contains(brand1)) {
                brand1List.add(description);
            }
            if (description.getText().contains(brand2)) {
                brand2List.add(description);
            }
        }

        if (brand1List.size() < 1 || brand2List.size() < 1) {
            return false;
        }
        return true;
    }

    public static boolean checkSaleNSFilter(String category) {
        List<SelenideElement> articles = $$(By.xpath(".//span[@data-auto-id='productTilePrice']/.."));

        for (SelenideElement article : articles) {
            if (category.equals("New Season") && article.findElements(By.xpath("./span")).size() > 1) {
                return false;
            }
            if (category.equals("Sale") && article.findElements(By.xpath("./span")).size() < 2) {
                return false;
            }
        }
        return true;
    }

    public static boolean selectPriceRange(String range) {
        SelenideElement minPrice = $(By.xpath(".//div[@aria-label='Minimum price']"));
        SelenideElement maxPrice = $(By.xpath(".//div[@aria-label='Maximum price']"));

        movePriceSliders(range, minPrice, maxPrice);

        maxPrice.shouldBe(Condition.visible);

        return comparePriceRanges(minPrice, maxPrice);
    }

    private static void movePriceSliders(String range, SelenideElement minPrice, SelenideElement maxPrice) {
        if (range.equals("left")) {
            Selenide.webdriver().driver().actions().dragAndDropBy(maxPrice, -100, 0).perform();
        }
        if (range.equals("right")) {
            Selenide.webdriver().driver().actions().dragAndDropBy(minPrice, 100, 0).perform();
        }
        if (range.equals("middle")) {
            Selenide.webdriver().driver().actions().dragAndDropBy(minPrice, 50, 0).perform();
            maxPrice.shouldBe(Condition.visible);
            Selenide.webdriver().driver().actions().dragAndDropBy(maxPrice, -50, 0).perform();
        }
    }

    private static boolean comparePriceRanges(SelenideElement minPrice, SelenideElement maxPrice) {
        int rangeBefore = Integer.parseInt(maxPrice.getAttribute("aria-valuemax")) - Integer.parseInt(minPrice.getAttribute("aria-valuemin"));
        int rangeNow = Integer.parseInt(maxPrice.getAttribute("aria-valuenow")) - Integer.parseInt(minPrice.getAttribute("aria-valuenow"));

        return rangeBefore > rangeNow;
    }

    public static boolean checkPriceRange() {
        checkPriceRangeVisible();

        List<SelenideElement> articles = $$(By.xpath(".//article"));
        List<Double> prices = getPrices(articles);
        SelenideElement minPrice = $(By.xpath(".//div[@aria-label='Minimum price']"));
        SelenideElement maxPrice = $(By.xpath(".//div[@aria-label='Maximum price']"));

        return comparePrices(maxPrice, minPrice, prices);
    }

    private static void checkPriceRangeVisible() {
        if (!$(By.xpath(".//div[@aria-label='Minimum price']")).is(Condition.visible)) {
            clickFilter("Price Range");
        }
    }

    private static boolean comparePrices(SelenideElement maxPrice, SelenideElement minPrice, List<Double> prices) {
        int min = Integer.parseInt(minPrice.getAttribute("aria-valuenow"));
        int max = Integer.parseInt(maxPrice.getAttribute("aria-valuenow"));

        for (Double price : prices) {
            if (price > max || price < min) {
                return false;
            }
        }
        return true;
    }


}
