package commons.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import commons.data.dataPage.BlogCategory;
import commons.data.dataPage.ItemType;
import commons.data.dataPage.Lang;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;


import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class BlogPage extends BasePage {

    ElementsCollection articles = $$(".article__item");

    public BlogPage checkSelectedPage(Lang locale, String expectedText) {
        return super.checkOnPage(locale, $("h1.text-center"), expectedText, BlogPage.class);
    }
    @Step("Select blog filter categories")
    public BlogPage selectBlogFilters(List<BlogCategory> blogCategory) {
        sleep(1500);
        $(".blog__search-wrap").shouldBe(Condition.visible);
        $(".blog__filters-drop").click();
        for (BlogCategory category: blogCategory) {
            category.getCategorySelector()
                    .findBy(Condition.have(Condition.text(category.getCategoryName()))).find("svg")
                    .click();
        }
        return this;
    }

    @Step("Check that any templates have any selected categories")
    public BlogPage checkArticleItemCategories(List<BlogCategory> categoriesName) {
        sleep(3000);
        ElementsCollection articleItemCategories = $$(".article__item-category");
        executeJavaScript("window.scrollBy(0,200)", "");
        for (SelenideElement articleCategory : articleItemCategories) {
            articleCategory.$$(".releases__status")
                    .shouldHave(CollectionCondition
                            .anyMatch("", element -> categoriesName.stream()
                                            .anyMatch(category -> category
                                                    .getCategoryName().toLowerCase()
                                                    .equals(element.getText().toLowerCase()))
                            ));
        }
        return this;
    }
    @Step("Click on the show next articles button")
    public BlogPage clickNext9Articles(int timeClick) {
        for (int i=0; i < timeClick; i++) {
            $("#load-article-nav .button-secondary").shouldBe(Condition.visible).click();
            sleep(400);
        }
        return this;
    }

    @Step("Check that show next articles button visible")
    public BlogPage checkNext9Articles(boolean expected) {
            $("#load-article-nav .button-secondary").shouldBe(expected? Condition.visible: Condition.not(Condition.exist));
        return this;
    }

    public BlogPage setItemType(ItemType itemType) {
        $(itemType.getSelector()).click();
        return this;
    }

    @Step("Enter value into search input and click search button")
    public BlogPage searchArticleByKeyWord(String articleName) {
        SelenideElement search = $(By.id("blog-search"));
        search.$(By.name("search")).shouldBe(Condition.visible).setValue(articleName);
        search.$("button").click();
        return this;
    }

    @Step("Check that any articles have expected text")
    public BlogPage checkAnyArticleHasText(String expectedArticleText) {
        $$(".article__item .article__item-title")
                .shouldHave(CollectionCondition.anyMatch("", article ->
                        article.getText().toUpperCase()
                        .contains(expectedArticleText.toUpperCase())));
        return this;
    }

    @Step("Check previous previous button exist")
    public BlogPage checkPreviousButton(boolean expected) {
        $("#load-article-nav [alt='arrow previous']").parent().shouldBe(expected?
                Condition.visible
                : Condition.not(Condition.exist));
        return this;
    }

    @Step("Check previous next 9 articles button exist")
    public BlogPage checkNextButton(boolean expected) {
        $("#load-article-nav [alt='arrow next']").shouldBe(expected?
                Condition.visible
                : Condition.not(Condition.exist));
        return this;
    }

    @Step("Click previous previous button")
    public BlogPage clickPreviousButton() {
        $("#load-article-nav [alt='arrow previous']").parent().click();
        return this;
    }

    @Step("Check previous next button")
    public BlogPage clickNextButton() {
        $("#load-article-nav [alt='arrow next']").parent().click();
        sleep(300);
        return this;
    }

    @Step("Click page on pagination panel")
    public BlogPage clickPage(int pageNumber) {
        $$("#load-article-nav .pagination li").findBy(Condition.have
                (Condition.text(String.valueOf(pageNumber))))
                .click();
        sleep(300);
        return this;
    }

    @Step("Check selected page on pagination panel")
    public BlogPage checkSelectedPage(int pageNumber) {
        sleep(200);
        $$("#load-article-nav .pagination li").findBy(Condition.text(String.valueOf(pageNumber))).shouldHave(Condition.cssClass("active"));
        return this;
    }

    @Step("Check the articles count")
    public BlogPage checkArticleItemCount(int count) {
        sleep(1500);
        Assert.assertEquals(getArticleItemCount(), count);
        return this;
    }

    public int getArticleItemCount() {
        sleep(2500);
        return $$(".article__item").size();
    }

    public BlogPage checkArticleReadTimeValue(String time) {
        $(".article__about-category").shouldHave(Condition.text(time));
        return this;
    }

    public BlogPage checkTitleValue(String title) {
        $(".scrolled-container h1").shouldHave(Condition.text(title));
        return this;
    }

    public BlogPage checkContentValue(String text) {
        $(".scrolled-content p").shouldHave(Condition.text(text));
        return this;
    }

    public BlogPage checkAuthorNameValue(String name) {
        $(".article__author h6").shouldHave(Condition.text(name));
        return this;
    }
}
