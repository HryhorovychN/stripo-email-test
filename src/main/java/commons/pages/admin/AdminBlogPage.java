package commons.pages.admin;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.SelectOptionByValue;
import commons.data.dataPage.BlogCategory;
import commons.data.dataPage.Lang;
import commons.pages.BlogPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.getUserAgent;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static commons.App.baseUrl;
import static commons.Driver.open;
import static commons.data.dataPage.Lang.EN;
import static java.lang.Enum.valueOf;

public class AdminBlogPage extends AdminPage {


    public AdminBlogPage checkCreateArticleButton() {
        $(".btn-success").shouldBe(Condition.visible).shouldHave(Condition.text("Create new article"));
        return this;
    }

    public AdminBlogPage clickCreateArticleButton() {
        $(".btn-success").click();
        return this;
    }

    @Override
        public AdminBlogPage checkOnPage(String expectedText) {
            return super.checkOnPage($(".container-fluid h3"), expectedText, AdminBlogPage.class);
        }

    public AdminBlogPage setArticleSlug(String slug) {
        $(By.id("slug")).shouldBe(Condition.visible).setValue(slug);
        return this;
    }

    public AdminBlogPage selectAuthor(String name) {
        Select select = new Select($(By.id("author_id")));
        select.selectByVisibleText(name);
        return this;
    }

    public AdminBlogPage setReadTime(String time) {
        $(By.id("read_time")).shouldBe(Condition.visible).setValue(time);
        return this;
    }

    public AdminBlogPage clickPublishButton() {
        $(By.id("publish_btn")).shouldBe(Condition.visible).click();
        return this;
    }

    public AdminBlogPage clickSaveButton() {
        $(".btn-success").shouldBe(Condition.visible).click();
        return this;
    }

    public AdminBlogPage clickViewOnSiteButton() {
        $$(".container-fluid a").get(1).shouldBe(Condition.visible).click();
        return this;
    }

    public AdminBlogPage clickBackButton() {
        $$(".container-fluid a").get(0).shouldBe(Condition.visible).click();
        return this;
    }

    public AdminBlogPage clickFirstArticle() {
        $$("#example1_wrapper a").get(0).shouldBe(Condition.visible).click();
        return this;
    }

    public AdminBlogPage checkFirstArticleName(String name) {
        $("#example1_wrapper td:not(:first-child)").shouldHave(Condition.text(name));
        return this;
    }

    public AdminBlogPage checkNotExistArticleName(String name) {
        $$("#example1_wrapper tr td:nth-child(2)")
                .shouldHave(CollectionCondition.allMatch(String.format("Check if exist article with name: %s in the List", name), element ->
                        !element.getText().equals(name)));
        return this;
    }

    public AdminBlogPage checkFirstArticlePublish(boolean expected) {
        $("#example1_wrapper .status i").shouldBe(expected?
                Condition.have(Condition.cssClass("ri-eye-fill"))
                : Condition.have(Condition.cssClass("ri-eye-off-fill")));
        return this;
    }

    public AdminBlogPage selectCategories(List<BlogCategory> categoriesName) {
        ElementsCollection categories = $$(".categories_check label");
        for (BlogCategory categoryName: categoriesName) {
            for (SelenideElement category: categories) {
                if (category.has(Condition.text(categoryName.getCategoryName()))) {
                    category.click();
                }
            }
        }
        return this;
    }

    public AdminBlogPage clickArticleLocale(Lang locale) {
        $$(".card-header li").findBy((Condition.text(locale.getLocale().toUpperCase()))).click();
        return this;
    }

    public AdminBlogPage setTitle(Lang locale, String text) {
        $(By.name(String.format("locale[%s][title]", locale.getLocale()))).shouldBe(Condition.visible).setValue(text);
        return this;
    }

    public AdminBlogPage setPreview(Lang locale, String text) {
        $(By.name(String.format("locale[%s][preview_title]", locale.getLocale()))).shouldBe(Condition.visible).setValue(text);
        return this;
    }

    public AdminBlogPage setContent(String text) {
        doInArticleContentFrame(() -> {
            sleep(1500);
            $("body").setValue(text);
        });
        return this;
    }

    public AdminBlogPage setBackgroundImg(String href) {
        $(By.name("background_id")).shouldBe(Condition.visible).setValue(href);
        return this;
    }

    public AdminBlogPage clickDeleteArticleByIdx(int index) {
        $$(".ri-delete-bin-line").get(index).click();
        return this;
    }

    public AdminBlogPage checkModalDialogConfirm(boolean expected) {
        $("#commonDeleteModal .modal-content").shouldBe(expected?
                Condition.enabled:
                not(Condition.exist));
        return this;
    }

    public AdminBlogPage confirmArticleDelete() {
        $("#common-popup-delete-submit").click();
        return this;
    }

    public BlogPage openArticleByName(Lang locale, String articleName) {
        open(String.format("%s/%s/blog/%s", baseUrl, locale.getLocale().toLowerCase(), articleName));
        return page(BlogPage.class);
    }


}


