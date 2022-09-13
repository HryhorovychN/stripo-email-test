package commons.pages;

import commons.data.dataPage.Locale;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage {

    public HomePage checkOnPage(Locale locale, String expectedText) {
        return super.checkOnPage(locale, $("h1.text-center"), expectedText, HomePage.class);
    }

    public HomePage checkTitlePage(Locale locale, String expectedTitle) {
        return super.checkTitlePage(locale, expectedTitle, HomePage.class);
    }

}
