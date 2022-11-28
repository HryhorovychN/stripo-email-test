package commons.pages;

import commons.data.dataPage.Lang;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage {

    public HomePage checkOnPage(Lang locale, String expectedText) {
        return super.checkOnPage(locale, $(".page-home__banner-left"), expectedText, HomePage.class);
    }

    public HomePage checkTitlePage(Lang locale, String expectedTitle) {
        return super.checkTitlePage(locale, expectedTitle, HomePage.class);
    }

}
